package org.jmasonry.vm.values;

final class LongValue implements Value {
    private final long value;

    LongValue(long value) {
        this.value = value;
    }

    @Override
    public <T> T map(Mapper<T> mapper) {
        return mapper.map(value);
    }

}
