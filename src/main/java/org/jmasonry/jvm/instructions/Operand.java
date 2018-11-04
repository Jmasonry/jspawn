package org.jmasonry.jvm.instructions;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

final class Operand {
    private final int operand;
    private final int byteCount;

    static Operand twoBytes(int operand) {
        return new Operand(operand, 2);
    }

    private Operand(int operand, int byteCount) {
        this.operand = operand;
        this.byteCount = byteCount;
    }

    void write(CompilationUnitBuilder builder) {
        if (byteCount == 2) {
            builder.appendTwoBytes(operand);
        } else {
            throw new UnsupportedOperationException("Unsupported byte count: " + byteCount);
        }
    }

    int byteCount() {
        return byteCount;
    }
}
