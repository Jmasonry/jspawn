package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.Type;
import org.jmasonry.jvm.dsl.ClassDefinition;

import java.util.List;
import java.util.stream.IntStream;

final class WriteSelfDeclaration implements CompilationStep {
    private static final short THIS_CLASS_OFFSET = 2;
    private static final short SUPER_CLASS_OFFSET = 4;
    private static final short ACCESS_FLAG = 0x0020;

    private final ClassDefinition definition;

    WriteSelfDeclaration(ClassDefinition definition) {
        this.definition = definition;
    }

    @Override
    public void execute(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(ACCESS_FLAG);
        builder.appendTwoBytes(THIS_CLASS_OFFSET);
        builder.appendTwoBytes(SUPER_CLASS_OFFSET);

        List<Type> interfaces = definition.getInterfaces();
        builder.appendTwoBytes(interfaces.size());
        IntStream.range(0, interfaces.size())
                .map(this::calculateInterfaceOffset)
                .forEach(builder::appendTwoBytes);
    }

    private int calculateInterfaceOffset(int i) {
        return 6 + i * 2;
    }
}
