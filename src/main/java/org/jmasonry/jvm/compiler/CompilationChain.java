package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.ClassFileVersion;
import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.dsl.ClassDefinition;
import org.jmasonry.jvm.types.Type;

import java.util.Arrays;
import java.util.List;

final class CompilationChain {
    private final ClassFileVersion version;
    private final ClassDefinition definition;

    CompilationChain(ClassFileVersion version, ClassDefinition definition) {
        this.version = version;
        this.definition = definition;
    }

    List<CompilationStep> getSteps() {
        ConstantPoolBuilder poolBuilder = createConstantPoolBuilder();

        return Arrays.asList(
                new WriteMagic(),
                new WriteVersion(version),
                new WriteConstantPool(poolBuilder),
                new WriteSelfDeclaration(definition),
                new WriteFields(),
                new WriteMethods(),
                new WriteAttributes()
        );
    }

    private ConstantPoolBuilder createConstantPoolBuilder() {
        ConstantPoolBuilder poolBuilder = new ConstantPoolBuilder();
        poolBuilder.appendClass(definition.getSelfType());
        poolBuilder.appendClass(definition.getSuperClass());
        for (Type anInterface : definition.getInterfaces()) {
            poolBuilder.appendClass(anInterface);
        }

        return poolBuilder;
    }
}
