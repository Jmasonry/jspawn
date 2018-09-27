package org.jmasonry.jvm.compiler;

final class WriteMagic implements CompilationStep {
    private static final int MAGIC = 0xCAFEBABE;

    @Override
    public void execute(CompilationUnitBuilder builder) {
        builder.appendFourBytes(MAGIC);
    }
}
