package org.jmasonry.jvm.compiler;

interface CompilationStep {
    void execute(CompilationUnitBuilder builder);
}
