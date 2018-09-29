package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.fields.FieldPool;
import org.jmasonry.jvm.classfile.fields.FieldPoolBuilder;
import org.jmasonry.jvm.classfile.fields.PooledField;

final class WriteFields implements CompilationStep {
    private FieldPoolBuilder poolBuilder;

    WriteFields(FieldPoolBuilder poolBuilder) {
        this.poolBuilder = poolBuilder;
    }

    @Override
    public void execute(CompilationUnitBuilder builder) {
        FieldPool pool = poolBuilder.build();

        builder.appendTwoBytes(pool.getSize());
        for (PooledField field : pool) {
            field.write(builder);
        }
    }
}
