package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

import java.util.Objects;

final class ClassConstant implements Constant {
    private final short nameIndex;

    ClassConstant(short nameIndex) {
        this.nameIndex = nameIndex;
    }

    @Override
    public void write(CompilationUnitBuilder builder) {
        builder.appendOneByte(7);
        builder.appendTwoBytes(nameIndex);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof ClassConstant)) {
            return false;
        }
        ClassConstant classInfo = (ClassConstant) object;
        return nameIndex == classInfo.nameIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameIndex);
    }
}
