package org.jmasonry.jvm.compiler;

final class WriteFields implements CompilationStep {
    @Override
    public void execute(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(0);      // currently fields are not supported
    }
}
