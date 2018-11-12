package org.jmasonry.jvm.instructions;

import java.util.Arrays;
import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public final class JvmInstruction {
   private final int       opcode;
   private final Operand[] operands;

   private final int byteCount;

   JvmInstruction(int opcode, Operand... operands) {
      this.opcode = opcode;
      this.operands = operands;
      byteCount = 1 + Arrays.stream(operands).mapToInt(Operand::byteCount).sum();
   }

   public void write(CompilationUnitBuilder builder) {
      builder.appendOneByte(opcode);
      for (Operand operand : operands) {
         operand.write(builder);
      }
   }

   public int byteCount() {
      return byteCount;
   }

   @Override
   public String toString() {
      return String.format("0x%s", Integer.toHexString(opcode));
   }
}
