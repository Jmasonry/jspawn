package org.jmasonry.jvm.classfile.fields;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public class PooledField {
    private static final short ACCESS_FLAG = FieldAccess.mask(FieldAccess.PRIVATE, FieldAccess.FINAL);
    private final short nameIndex;
    private final short typeIndex;

    PooledField(short nameIndex, short typeIndex) {
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
