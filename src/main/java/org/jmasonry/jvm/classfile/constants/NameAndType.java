package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

import java.util.Objects;

final class NameAndType implements Constant {
    private final int nameIndex;
    private final int descriptorIndex;

    NameAndType(int nameIndex, int descriptorIndex) {
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
    }

    @Override
    public void write(CompilationUnitBuilder builder) {
        builder.appendOneByte(12);
        builder.appendTwoBytes(nameIndex);
        builder.appendTwoBytes(descriptorIndex);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof NameAndType)) {
            return false;
        }
        NameAndType that = (NameAndType) object;
        return nameIndex == that.nameIndex &&
                descriptorIndex == that.descriptorIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameIndex, descriptorIndex);
    }
}
