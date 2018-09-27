package org.jmasonry.jvm.compiler;

public class WriteAttributes implements CompilationStep {
    @Override
    public void execute(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(0);      // currently attributes are not supported
    }
}
