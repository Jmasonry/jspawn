package org.jmasonry.jvm.classfile.constants;


import static org.jmasonry.jvm.classfile.constants.ConstantFactory.classConst;
import static org.jmasonry.jvm.classfile.constants.ConstantFactory.methodRef;
import static org.jmasonry.jvm.classfile.constants.ConstantFactory.nameAndType;
import static org.jmasonry.jvm.classfile.constants.ConstantFactory.padding;
import static org.jmasonry.jvm.classfile.constants.ConstantFactory.utf8Const;
import static org.jmasonry.jvm.classfile.constants.ConstantFactory.value;
import static org.jmasonry.jvm.values.Precision.EIGHT_BYTES;

import java.util.LinkedHashMap;
import java.util.Map;
import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.values.Precision;
import org.jmasonry.vm.values.Value;

public final class ConstantPoolBuilder {
   private final Map<Constant, Integer> cache = new LinkedHashMap<>();

   public int appendValue(Value operand) {
      var constant = value(operand);
      var offset = getOrCreate(constant);

      if (Precision.of(operand) == EIGHT_BYTES) {
         getOrCreate(padding());
      }

      return offset;
   }

   public int appendUTF8(String string) {
      var constant = utf8Const(string);
      return getOrCreate(constant);
   }

   public int appendClass(Type type) {
      var typeName = type.getInternalName();
      var nameIndex = appendUTF8(typeName);

      var constant = classConst(nameIndex);
      return getOrCreate(constant);
   }

   private int appendNameAndType(String name, String descriptor) {
      var nameIndex = appendUTF8(name);
      var descriptorIndex = appendUTF8(descriptor);

      var constant = nameAndType(nameIndex, descriptorIndex);
      return getOrCreate(constant);
   }

   public int appendMethod(Type classType, MethodDeclaration methodDeclaration) {
      var classIndex = appendClass(classType);
      var nameAndTypeIndex = appendNameAndType(methodDeclaration.getName(), methodDeclaration.getDescriptor());

      var constant = methodRef(classIndex, nameAndTypeIndex);
      return getOrCreate(constant);
   }

   public ConstantPool build() {
      var constants = new LinkedHashMap<Integer, Constant>();
      for (var entry : cache.entrySet()) {
         constants.put(entry.getValue(), entry.getKey());
      }
      return new ConstantPool(constants);
   }

   private int getOrCreate(Constant constant) {
      return cache.computeIfAbsent(constant, k -> offset());
   }

   private int offset() {
      return cache.size() + 1;
   }
}