package org.jmasonry.jvm.instructions;

import static org.jmasonry.jvm.instructions.Operand.oneByte;
import static org.jmasonry.jvm.instructions.Operand.twoBytes;
import static org.jmasonry.jvm.instructions.PushInstructions.push;

import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.values.Precision;
import org.jmasonry.vm.values.Value;

public final class JvmInstructions {
   public static JvmInstruction returnValueOfType(Type type) {
      switch (type.getDescriptor()) {
         case "B":
         case "C":
         case "S":
         case "I": return new JvmInstruction(0xac);
         case "J": return new JvmInstruction(0xad);
         case "F": return new JvmInstruction(0xae);
         case "D": return new JvmInstruction(0xaf);
         case "V": return new JvmInstruction(0xb1);
         default: throw new IllegalArgumentException("Unsupported return type: " + type.getDescriptor());
      }
   }

   public static JvmInstruction pushValue(Value value) {
      return push(value);
   }

   public static JvmInstruction invokeSpecial(int value) {
      return new JvmInstruction(0xb7, twoBytes(value));
   }

   public static JvmInstruction loadConstant(int constantIndex, Precision precision) {
      if (precision == Precision.EIGHT_BYTES) {
         return new JvmInstruction(0x14, twoBytes(constantIndex));
      } else {
         return new JvmInstruction(0x12, oneByte(constantIndex));
      }
   }

   public static JvmInstruction loadReference(int value) {
      return new JvmInstruction(0x19, twoBytes(value));
   }

   public static JvmInstruction convertPrimitives(Type from, Type to) {
      String conversionDescriptor = from.getDescriptor() + to.getDescriptor();
      switch (conversionDescriptor) {
         case "IJ": return new JvmInstruction(0x85);
         case "IF": return new JvmInstruction(0x86);
         case "ID": return new JvmInstruction(0x87);

         case "JI": return new JvmInstruction(0x88);
         case "JF": return new JvmInstruction(0x89);
         case "JD": return new JvmInstruction(0x8a);

         case "FI": return new JvmInstruction(0x8b);
         case "FJ": return new JvmInstruction(0x8c);
         case "FD": return new JvmInstruction(0x8d);

         case "DI": return new JvmInstruction(0x8e);
         case "DJ": return new JvmInstruction(0x8f);
         case "DF": return new JvmInstruction(0x90);

         case "IB": return new JvmInstruction(0x91);
         case "IC": return new JvmInstruction(0x92);
         case "IS": return new JvmInstruction(0x93);

         default: throw new IllegalArgumentException("Unsupported convesion: " + conversionDescriptor);
      }
   }
}
