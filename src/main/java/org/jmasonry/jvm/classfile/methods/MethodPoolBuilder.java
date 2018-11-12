package org.jmasonry.jvm.classfile.methods;

import java.util.ArrayList;
import java.util.List;
import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.classfile.methods.attributes.MethodAttributesFactory;
import org.jmasonry.jvm.types.MethodDefinition;

public final class MethodPoolBuilder {
   private final ConstantPoolBuilder     constants;
   private final MethodAttributesFactory attributesFactory;

   private final List<PooledMethod> methods = new ArrayList<>();

   public MethodPoolBuilder(ConstantPoolBuilder constants) {
      this.constants = constants;
      attributesFactory = new MethodAttributesFactory(constants);
   }

   public void add(MethodDefinition definition) {
      PooledMethod method = create(definition);
      methods.add(method);
   }

   private PooledMethod create(MethodDefinition definition) {
      var declaration = definition.getDeclaration();
      var nameIndex = constants.appendUTF8(declaration.getName());
      var typeIndex = constants.appendUTF8(declaration.getDescriptor());

      var attributes = attributesFactory.create(definition);
      return new PooledMethod(nameIndex, typeIndex, attributes);
   }

   public MethodPool build() {
      return new MethodPool(methods);
   }
}
