package org.jmasonry.vm.values;

public interface Value {
    static Value of(int value) { return new IntegerValue(value);}

    static Value of(long value) { return new LongValue(value);}

    static Value of(float value) { return new FloatValue(value);}

    static Value of(double value) { return new DoubleValue(value);}

    <T> T map(Mapper<T> mapper);

    interface Mapper<T> {
        T map(int value);

        T map(long value);

        T map(float value);

        T map(double value);
    }
}
