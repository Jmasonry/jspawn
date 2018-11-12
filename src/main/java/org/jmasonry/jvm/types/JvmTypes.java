package org.jmasonry.jvm.types;

import org.jmasonry.jvm.values.Precision;

public final class JvmTypes {
   public static Precision precisionOf(Type type) {
      switch (type.getDescriptor()) {
         case "V": return Precision.ZERO_BYTES;
         case "B": return Precision.ONE_BYTE;
         case "S": return Precision.TWO_BYTES;
         case "J":
         case "D": return Precision.EIGHT_BYTES;
         case "I":
         case "F":
         default: return Precision.FOUR_BYTES;
      }
   }
}
