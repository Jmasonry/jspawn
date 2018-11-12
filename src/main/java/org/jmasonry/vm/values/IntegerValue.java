package org.jmasonry.vm.values;

import org.jmasonry.jvm.values.Precision;

final class IntegerValue implements Value {
    private final int value;
    private final Precision precision;

    IntegerValue(int value) {
        this.value = value;
        precision = Precision.FOUR_BYTES;
    }

    @Override
    public <T> T map(Mapper<T> mapper) {
        return mapper.map(value);
    }

}
