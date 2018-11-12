package org.jmasonry.vm.values;

final class DoubleValue implements Value {
   private final double value;

   DoubleValue(double value) {
      this.value = value;
   }

   @Override
   public <T> T map(Mapper<T> mapper) {
      return mapper.map(value);
   }
}
