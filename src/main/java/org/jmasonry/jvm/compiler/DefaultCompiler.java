package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.ClassFile;
import org.jmasonry.jvm.classfile.ClassFileFactory;
import org.jmasonry.jvm.classfile.ClassFileVersion;
import org.jmasonry.jvm.types.TypeDefinition;

public final class DefaultCompiler implements Compiler {
    private static final ClassFileVersion version = ClassFileVersion.JAVA_10;
    private final ClassFileFactory classFileFactory = new ClassFileFactory(version);

    @Override
    public byte[] compile(TypeDefinition type) {
        CompilationUnitBuilder builder = CompilationUnitBuilder.createInMemory();

        ClassFile classFile = classFileFactory.create(type);
        classFile.write(builder);

        return builder.build();
    }

}
