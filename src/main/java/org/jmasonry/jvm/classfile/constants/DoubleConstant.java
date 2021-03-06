package org.jmasonry.jvm.classfile.constants;

import static java.lang.Double.doubleToLongBits;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

final class DoubleConstant implements Constant {
   private final double value;

   DoubleConstant(double value) {
      this.value = value;
   }

   @Override
   public void write(CompilationUnitBuilder builder) {
      builder.appendOneByte(6);
      builder.appendEightBytes(doubleToLongBits(value));
   }
}
