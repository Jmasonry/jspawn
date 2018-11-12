package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

final class LongConstant implements Constant {
    private final long value;

    LongConstant(long value) {
        this.value = value;
    }

    @Override
    public void write(CompilationUnitBuilder builder) {
        builder.appendOneByte(5);
        builder.appendEightBytes(value);
    }
}
