package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

final class IntegerConstant implements Constant {
    private final int bytes;

    IntegerConstant(int bytes) {
        this.bytes = bytes;
    }

    @Override
    public void write(CompilationUnitBuilder builder) {
        builder.appendOneByte(3);
        builder.appendFourBytes(bytes);
    }
}
