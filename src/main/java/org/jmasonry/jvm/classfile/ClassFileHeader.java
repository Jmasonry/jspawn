package org.jmasonry.jvm.classfile;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

final class ClassFileHeader {
    private final int accessFlags;
    private final int thisClassIndex;
    private final int superClassIndex;
    private final int[] interfaceIndexes;

    ClassFileHeader(int accessFlags, int thisClassIndex, int superClassIndex, int[] interfaceIndexes) {
        this.accessFlags = accessFlags;
        this.thisClassIndex = thisClassIndex;
        this.superClassIndex = superClassIndex;
        this.interfaceIndexes = interfaceIndexes;
    }

    public void write(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(accessFlags);
        builder.appendTwoBytes(thisClassIndex);
        builder.appendTwoBytes(superClassIndex);

        builder.appendTwoBytes(interfaceIndexes.length);
        for (int interfaceIndex : interfaceIndexes) {
            builder.appendTwoBytes(interfaceIndex);
        }
    }
}
