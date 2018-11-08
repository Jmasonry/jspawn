package org.jmasonry.jvm.instructions;

import org.jmasonry.jvm.types.Type;

import static org.jmasonry.jvm.instructions.Operand.oneByte;
import static org.jmasonry.jvm.instructions.Operand.twoBytes;

public final class JvmInstructions {
    public static final JvmInstruction RETURN_TOP = new JvmInstruction(0xB1);

    public static JvmInstruction RETURN(Type type) {
        switch (type.getDescriptor()) {
            case "I": return new JvmInstruction(0xac);
            default: return new JvmInstruction(0xB1);
        }
    }

    public static JvmInstruction INVOKE_SPECIAL(int value) {
        return new JvmInstruction(0xb7, twoBytes(value));
    }

    public static JvmInstruction A_LOAD(int value) {
        return new JvmInstruction(0x19, twoBytes(value));
    }

    public static JvmInstruction LOAD_CONSTANT(int constantIndex) {
        return new JvmInstruction(0x12, oneByte(constantIndex));
    }
}
