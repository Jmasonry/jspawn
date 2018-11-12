package org.jmasonry.jvm.values;

import org.jmasonry.vm.values.Value;

public enum Precision {
   BYTE(1),
   TWO_BYTES(2),
   FOUR_BYTES(4),
   EIGHT_BYTES(8);

   private static final PrecisionMapper mapper = new PrecisionMapper();

   private final int bytes;

   Precision(int bytes) {
      this.bytes = bytes;
   }

   public static Precision of(Value value) {
      return value.map(mapper);
   }

   public int bytes() {
      return bytes;
   }
}
