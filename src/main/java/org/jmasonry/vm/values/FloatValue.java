package org.jmasonry.vm.values;

final class FloatValue implements Value {
   private final float value;

   FloatValue(float value) {
      this.value = value;
   }

   @Override
   public <T> T map(Mapper<T> mapper) {
      return mapper.map(value);
   }

}
