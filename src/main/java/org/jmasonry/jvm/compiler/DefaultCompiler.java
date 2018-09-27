package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.ClassFileVersion;
import org.jmasonry.jvm.dsl.ClassDefinition;

import java.util.List;

public final class DefaultCompiler implements Compiler {
    private static final ClassFileVersion version = ClassFileVersion.JAVA_10;

    @Override
    public byte[] compile(ClassDefinition definition) {
        CompilationUnitBuilder builder = CompilationUnitBuilder.createInMemory();
        List<CompilationStep> compilationChain = createCompilationChain(definition);

        for (CompilationStep step : compilationChain) {
            step.execute(builder);
        }

        return builder.build();
    }

    private List<CompilationStep> createCompilationChain(ClassDefinition definition) {
        return new CompilationChain(version, definition).getSteps();
    }
}
