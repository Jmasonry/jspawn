package org.jmasonry.jvm.classfile;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public enum ClassFileVersion {
    JAVA_10(53, 0);

    private final short major;
    private final short minor;

    ClassFileVersion(int major, int minor) {
        this.major = (short) major;
        this.minor = (short) minor;
    }

    public void write(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(minor);
        builder.appendTwoBytes(major);
    }
}
