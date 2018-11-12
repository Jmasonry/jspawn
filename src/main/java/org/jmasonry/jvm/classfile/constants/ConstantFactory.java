package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.vm.values.Value;

final class ConstantFactory {
   private static final ValueConstantFactory valueConstantFactory = new ValueConstantFactory();

   static Constant utf8Const(String string) {
      return new UTF8Constant(string);
   }

   static Constant value(Value value) {
      return value.map(valueConstantFactory);
   }

   static Constant classConst(int nameIndex) {
      return new ClassConstant(nameIndex);
   }

   static Constant nameAndType(int nameIndex, int descriptorIndex) {
      return new NameAndType(nameIndex, descriptorIndex);
   }

   static Constant methodRef(int classIndex, int nameAndTypeIndex) {
      return new MethodRef(classIndex, nameAndTypeIndex);
   }

   static Constant padding() {
      return new Padding();
   }
}
