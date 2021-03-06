package org.jmasonry.jvm.classfile;

import java.util.List;
import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.classfile.fields.FieldPool;
import org.jmasonry.jvm.classfile.fields.FieldPoolBuilder;
import org.jmasonry.jvm.classfile.methods.MethodPool;
import org.jmasonry.jvm.classfile.methods.MethodPoolBuilder;
import org.jmasonry.jvm.types.FieldDeclaration;
import org.jmasonry.jvm.types.MethodDefinition;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;

public final class ClassFileFactory {
   private final ClassFileVersion version;

   public ClassFileFactory(ClassFileVersion version) {
      this.version = version;
   }

   public ClassFile create(TypeDefinition typeDefinition) {
      var constants = new ConstantPoolBuilder();

      var header = createHeader(constants, typeDefinition.getDeclaration());
      var fields = createFields(constants, typeDefinition.getFields());
      var methods = createMethods(constants, typeDefinition.getMethods());

      return new ClassFile(version, header, constants.build(), fields, methods);
   }

   private ClassFileHeader createHeader(ConstantPoolBuilder constants, TypeDeclaration declaration) {
      var accessFlags = ClassAccess.mask(ClassAccess.PUBLIC, ClassAccess.SUPER);
      var thisClassIndex = constants.appendClass(declaration.getSelfType());
      var superClassIndex = constants.appendClass(declaration.getSuperClass());

      var interfaceIndexes = declaration.getInterfaces().stream()
          .mapToInt(constants::appendClass)
          .toArray();

      return new ClassFileHeader(accessFlags, thisClassIndex, superClassIndex, interfaceIndexes);
   }

   private FieldPool createFields(ConstantPoolBuilder constants, List<FieldDeclaration> fields) {
      var pool = new FieldPoolBuilder(constants);
      for (FieldDeclaration field : fields) {
         pool.add(field);
      }
      return pool.build();
   }

   private MethodPool createMethods(ConstantPoolBuilder constants, List<MethodDefinition> methods) {
      var pool = new MethodPoolBuilder(constants);
      for (var method : methods) {
         pool.add(method);
      }
      return pool.build();
   }
}
