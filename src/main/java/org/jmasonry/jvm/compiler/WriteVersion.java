package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.ClassFileVersion;

final class WriteVersion implements CompilationStep {
    private final ClassFileVersion version;

    WriteVersion(ClassFileVersion version) {this.version = version;}

    @Override
    public void execute(CompilationUnitBuilder builder) {
        version.write(builder);
    }
}
