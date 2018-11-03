package org.jmasonry.jvm.classfile.fields;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public final class PooledField {
    private static final int ACCESS_FLAG = FieldAccess.mask(FieldAccess.PRIVATE, FieldAccess.FINAL);
    private final int nameIndex;
    private final int typeIndex;

    PooledField(int nameIndex, int typeIndex) {
        this.nameIndex = nameIndex;
        this.typeIndex = typeIndex;
    }

    public void write(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(ACCESS_FLAG);
        builder.appendTwoBytes(nameIndex);
        builder.appendTwoBytes(typeIndex);
        builder.appendTwoBytes(0);                      // attributes are not (yet?) supported
    }
}
