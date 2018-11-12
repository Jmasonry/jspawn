package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

final class IntegerConstant implements Constant {
   private final int value;

   IntegerConstant(int value) {
      this.value = value;
   }

   @Override
   public void write(CompilationUnitBuilder builder) {
      builder.appendOneByte(3);
      builder.appendFourBytes(value);
   }
}
