package org.jmasonry.jvm.classfile.methods.attributes.code;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.jmasonry.jvm.instructions.JvmInstruction;

final class Bytecode implements Iterable<JvmInstruction> {
   private final List<JvmInstruction> instructions = new ArrayList<>();

   private int byteCount = 0;

   @Override
   public Iterator<JvmInstruction> iterator() {
      return instructions.iterator();
   }

   int byteCount() {
      return byteCount;
   }

   void append(JvmInstruction instruction) {
      instructions.add(instruction);
      byteCount += instruction.byteCount();
   }
}
