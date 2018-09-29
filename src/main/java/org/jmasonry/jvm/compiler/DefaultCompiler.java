package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.ClassFileVersion;
import org.jmasonry.jvm.types.TypeDefinition;

import java.util.List;

public final class DefaultCompiler implements Compiler {
    private static final ClassFileVersion version = ClassFileVersion.JAVA_10;

    @Override
    public byte[] compile(TypeDefinition type) {
        CompilationUnitBuilder builder = CompilationUnitBuilder.createInMemory();
        List<CompilationStep> compilationChain = createCompilationChain(type);

        for (CompilationStep step : compilationChain) {
            step.execute(builder);
        }

        return builder.build();
    }

    private List<CompilationStep> createCompilationChain(TypeDefinition type) {
        return new CompilationChain(version, type).getSteps();
    }
}
