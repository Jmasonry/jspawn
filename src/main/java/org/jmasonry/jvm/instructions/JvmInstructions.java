package org.jmasonry.jvm.instructions;

import org.jmasonry.jvm.types.Type;

import static org.jmasonry.jvm.instructions.Operand.oneByte;
import static org.jmasonry.jvm.instructions.Operand.twoBytes;

public final class JvmInstructions {

    public static JvmInstruction returnValueOfType(Type type) {
        switch (type.getDescriptor()) {
            case "I": return new JvmInstruction(0xac);
            default: return new JvmInstruction(0xb1);
        }
    }

    public static JvmInstruction invokeSpecial(int value) {
        return new JvmInstruction(0xb7, twoBytes(value));
    }

    public static JvmInstruction loadConstant(int constantIndex) {
        return new JvmInstruction(0x12, oneByte(constantIndex));
    }

    public static JvmInstruction loadReference(int value) {
        return new JvmInstruction(0x19, twoBytes(value));
    }
}
