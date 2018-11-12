package org.jmasonry.jvm.values;

import org.jmasonry.vm.values.Value;

final class PrecisionMapper implements Value.Mapper<Precision> {

   @Override
   public Precision map(int value) {
      if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
         return Precision.BYTE;
      }

      if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
         return Precision.TWO_BYTES;
      }

      return Precision.FOUR_BYTES;
   }

   @Override
   public Precision map(long value) {
      return Precision.EIGHT_BYTES;
   }

   @Override
   public Precision map(float value) {
      return Precision.FOUR_BYTES;
   }

   @Override
   public Precision map(double value) {
      return Precision.EIGHT_BYTES;
   }
}
