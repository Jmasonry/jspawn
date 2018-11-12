package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.vm.values.Value;

final class ValueConstantFactory implements Value.Mapper<Constant> {
   @Override
   public Constant map(int value) {
      return new IntegerConstant(value);
   }

   @Override
   public Constant map(long value) {
      return new LongConstant(value);
   }

   @Override
   public Constant map(float value) {
      return new FloatConstant(value);
   }

   @Override
   public Constant map(double value) {
      return new DoubleConstant(value);
   }
}
