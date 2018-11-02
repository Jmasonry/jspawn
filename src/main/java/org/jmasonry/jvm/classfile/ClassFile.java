package org.jmasonry.jvm.classfile;

import org.jmasonry.jvm.classfile.constants.ConstantPool;
import org.jmasonry.jvm.classfile.fields.FieldPool;
import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public final class ClassFile {
    private final ClassFileVersion version;
    private final ClassFileHeader header;
    private final ConstantPool constants;
    private final FieldPool fields;

    ClassFile(ClassFileVersion version, ClassFileHeader header, ConstantPool constants, FieldPool fields) {
        this.version = version;
        this.header = header;
        this.constants = constants;
        this.fields = fields;
    }

    public void write(CompilationUnitBuilder builder) {
        builder.appendFourBytes(0xCAFEBABE);
        version.write(builder);
        constants.write(builder);
        header.write(builder);
        fields.write(builder);

        builder.appendTwoBytes(0);  // methods
        builder.appendTwoBytes(0);  // attributes
    }
}
