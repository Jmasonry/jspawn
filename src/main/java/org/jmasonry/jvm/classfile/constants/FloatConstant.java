package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

import static java.lang.Float.floatToIntBits;

final class FloatConstant implements Constant {
    private final float value;

    FloatConstant(float value) {
        this.value = value;
    }

    @Override
    public void write(CompilationUnitBuilder builder) {
        builder.appendOneByte(4);
        builder.appendFourBytes(floatToIntBits(value));
    }
}
