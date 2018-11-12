package org.jmasonry.vm.values;

final class IntegerValue implements Value {
   private final int value;

   IntegerValue(int value) {
      this.value = value;
   }

   @Override
   public <T> T map(Mapper<T> mapper) {
      return mapper.map(value);
   }

}
