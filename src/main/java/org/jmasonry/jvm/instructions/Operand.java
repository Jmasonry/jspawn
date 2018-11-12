package org.jmasonry.jvm.instructions;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

final class Operand {
   private final int operand;
   private final int byteCount;

   private Operand(int operand, int byteCount) {
      this.operand = operand;
      this.byteCount = byteCount;
   }

   static Operand oneByte(int operand) {
      return new Operand(operand, 1);
   }

   static Operand twoBytes(int operand) {
      return new Operand(operand, 2);
   }

   void write(CompilationUnitBuilder builder) {
      if (byteCount == 1) {
         builder.appendOneByte(operand);
      } else if (byteCount == 2) {
         builder.appendTwoBytes(operand);
      } else {
         throw new UnsupportedOperationException("Unsupported byte count: " + byteCount);
      }
   }

   int byteCount() {
      return byteCount;
   }
}
