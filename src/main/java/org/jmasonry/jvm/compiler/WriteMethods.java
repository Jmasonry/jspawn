package org.jmasonry.jvm.compiler;

public class WriteMethods implements CompilationStep {
    @Override
    public void execute(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(0);      // currently methods are not supported
    }
}
