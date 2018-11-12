package org.jmasonry.jvm.classfile.methods;

import org.jmasonry.jvm.classfile.attributes.AttributePool;
import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public final class PooledMethod {
   private static final int ACCESS_FLAG = MethodAccess.mask(MethodAccess.PUBLIC);

   private final int nameIndex;
   private final int typeIndex;

   private final AttributePool attributes;

   PooledMethod(int nameIndex, int typeIndex, AttributePool attributes) {
      this.nameIndex = nameIndex;
      this.typeIndex = typeIndex;
      this.attributes = attributes;
   }

   public void write(CompilationUnitBuilder builder) {
      builder.appendTwoBytes(ACCESS_FLAG);
      builder.appendTwoBytes(nameIndex);
      builder.appendTwoBytes(typeIndex);
      attributes.write(builder);
   }
}
