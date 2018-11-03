package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

import java.util.Objects;

final class MethodRef implements Constant {
    private final int classIndex;
    private final int nameAndTypeIndex;

    MethodRef(int classIndex, int nameAndTypeIndex) {
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    @Override
    public void write(CompilationUnitBuilder builder) {
        builder.appendOneByte(10);
        builder.appendTwoBytes(classIndex);
        builder.appendTwoBytes(nameAndTypeIndex);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof MethodRef)) {
            return false;
        }
        MethodRef that = (MethodRef) object;
        return classIndex == that.classIndex &&
                nameAndTypeIndex == that.nameAndTypeIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(classIndex, nameAndTypeIndex);
    }
}
