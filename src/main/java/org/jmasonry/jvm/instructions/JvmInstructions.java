package org.jmasonry.jvm.instructions;

import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.values.Precision;
import org.jmasonry.vm.values.Value;

import static org.jmasonry.jvm.instructions.Operand.oneByte;
import static org.jmasonry.jvm.instructions.Operand.twoBytes;
import static org.jmasonry.jvm.instructions.PushInstructions.push;

public final class JvmInstructions {

    public static JvmInstruction returnValueOfType(Type type) {
        switch (type.getDescriptor()) {
            case "B":
            case "S":
            case "I": return new JvmInstruction(0xac);
            case "J": return new JvmInstruction(0xad);
            case "F": return new JvmInstruction(0xae);
            case "D": return new JvmInstruction(0xaf);
            case "V": return new JvmInstruction(0xb1);
            default: throw new IllegalArgumentException("Unsupported return type: " + type.getDescriptor());
        }
    }

    public static JvmInstruction pushValue(Value value) {
        return push(value);
    }

    public static JvmInstruction invokeSpecial(int value) {
        return new JvmInstruction(0xb7, twoBytes(value));
    }

    public static JvmInstruction loadConstant(int constantIndex, Precision precision) {
        if (precision == Precision.EIGHT_BYTES) {
            return new JvmInstruction(0x14, twoBytes(constantIndex));
        } else {
            return new JvmInstruction(0x12, oneByte(constantIndex));
        }
    }

    public static JvmInstruction loadReference(int value) {
        return new JvmInstruction(0x19, twoBytes(value));
    }
}
