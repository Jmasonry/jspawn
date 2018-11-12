package org.jmasonry.jvm.instructions;

import org.jmasonry.vm.values.Value;

import static org.jmasonry.jvm.instructions.Operand.oneByte;
import static org.jmasonry.jvm.instructions.Operand.twoBytes;

final class PushInstructions implements Value.Mapper<JvmInstruction> {
    private static final PushInstructions FACTORY = new PushInstructions();

    static JvmInstruction push(Value value) {
        return value.map(FACTORY);
    }

    @Override
    public JvmInstruction map(int value) {
        if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE) {
            return new JvmInstruction(0x10, oneByte(value));
        }
        if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE) {
            return new JvmInstruction(0x11, twoBytes(value));
        }

        throw illegalPushValue(value);
    }

    @Override
    public JvmInstruction map(long value) {
        throw illegalPushValue(value);
    }

    @Override
    public JvmInstruction map(float value) {
        throw illegalPushValue(value);
    }

    @Override
    public JvmInstruction map(double value) {
        throw illegalPushValue(value);
    }

    private IllegalStateException illegalPushValue(Number value) {
        return new IllegalStateException("Value cannot be pushed on stack: " + value);
    }
}
