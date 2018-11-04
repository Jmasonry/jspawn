package org.jmasonry.vm.stack.instructions;

import org.jmasonry.jvm.types.Type;

final class Return implements StackInstruction {
    private final Type type;

    Return(Type type) {
        this.type = type;
    }

    @Override
    public void interpret(Interpreter interpreter) {
        interpreter.returnTop(type);
    }
}
