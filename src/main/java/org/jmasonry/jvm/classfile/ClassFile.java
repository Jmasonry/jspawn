package org.jmasonry.jvm.classfile;

import org.jmasonry.jvm.classfile.constants.ConstantPool;
import org.jmasonry.jvm.classfile.fields.FieldPool;
import org.jmasonry.jvm.classfile.methods.MethodPool;
import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public final class ClassFile {
    private final ClassFileVersion version;
    private final ClassFileHeader header;
    private final ConstantPool constants;
    private final FieldPool fields;
    private final MethodPool methods;

    ClassFile(ClassFileVersion version, ClassFileHeader header, ConstantPool constants, FieldPool fields, MethodPool methods) {
        this.version = version;
        this.header = header;
        this.constants = constants;
        this.fields = fields;
        this.methods = methods;
    }

    public void write(CompilationUnitBuilder builder) {
        builder.appendFourBytes(0xCAFEBABE);
        version.write(builder);
        constants.write(builder);
        header.write(builder);
        fields.write(builder);
        methods.write(builder);
        builder.appendTwoBytes(0);  // attributes
    }
}
