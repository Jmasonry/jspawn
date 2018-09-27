package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.constants.Constant;
import org.jmasonry.jvm.classfile.constants.ConstantPool;
import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;

final class WriteConstantPool implements CompilationStep {
    private final ConstantPoolBuilder poolBuilder;

    WriteConstantPool(ConstantPoolBuilder poolBuilder) {this.poolBuilder = poolBuilder;}

    @Override
    public void execute(CompilationUnitBuilder builder) {
        ConstantPool constantPool = poolBuilder.build();
        builder.appendTwoBytes(constantPool.getSize() + 1);
        for (Constant constant : constantPool) {
            constant.write(builder);
        }
    }
}
