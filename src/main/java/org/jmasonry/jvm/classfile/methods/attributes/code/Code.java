package org.jmasonry.jvm.classfile.methods.attributes.code;

import org.jmasonry.jvm.classfile.attributes.PooledAttribute;
import org.jmasonry.jvm.compiler.CompilationUnitBuilder;
import org.jmasonry.jvm.instructions.JvmInstruction;

public final class Code implements PooledAttribute {
    private final int nameIndex;
    private final int maxStackSize;
    private final int parameterCount;
    private final Bytecode bytecode;

    Code(int nameIndex, int maxStackSize, int parameterCount, Bytecode bytecode) {
        this.nameIndex = nameIndex;
        this.maxStackSize = maxStackSize;
        this.parameterCount = parameterCount;
        this.bytecode = bytecode;
    }

    @Override
    public void write(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(nameIndex);
        builder.appendFourBytes(12 + bytecode.byteCount()); // total size (except first six bytes)

        builder.appendTwoBytes(maxStackSize);
        builder.appendTwoBytes(parameterCount);

        builder.appendFourBytes(bytecode.byteCount());
        for (JvmInstruction instruction : bytecode) {
            instruction.write(builder);
        }

        builder.appendTwoBytes(0);                      // exceptions are not (yet?) supported
        builder.appendTwoBytes(0);                      // attributes are not (yet?) supported
    }
}

