package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.ClassFileVersion;
import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.classfile.fields.FieldPoolBuilder;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;

import java.util.Arrays;
import java.util.List;

final class CompilationChain {
    private final ClassFileVersion version;
    private final TypeDefinition type;

    CompilationChain(ClassFileVersion version, TypeDefinition type) {
        this.version = version;
        this.type = type;
    }

    List<CompilationStep> getSteps() {
        ConstantPoolBuilder constPoolBuilder = createConstantPoolBuilder();
        FieldPoolBuilder fieldPoolBuilder = new FieldPoolBuilder(constPoolBuilder);

        return Arrays.asList(
                new AnalyseFields(fieldPoolBuilder, type.getFields()),

                new WriteMagic(),
                new WriteVersion(version),
                new WriteConstantPool(constPoolBuilder),
                new WriteSelfDeclaration(type.getDeclaration()),
                new WriteFields(fieldPoolBuilder),
                new WriteMethods(),
                new WriteAttributes()
        );
    }

    private ConstantPoolBuilder createConstantPoolBuilder() {
        ConstantPoolBuilder poolBuilder = new ConstantPoolBuilder();
        TypeDeclaration declaration = type.getDeclaration();
        poolBuilder.appendClass(declaration.getSelfType());
        poolBuilder.appendClass(declaration.getSuperClass());
        for (Type anInterface : declaration.getInterfaces()) {
            poolBuilder.appendClass(anInterface);
        }

        return poolBuilder;
    }
}
