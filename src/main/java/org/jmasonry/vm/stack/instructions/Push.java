package org.jmasonry.vm.stack.instructions;

final class Push implements StackInstruction {
    private final int value;

    Push(int value) {
        this.value = value;
    }

    @Override
    public void interpret(Interpreter interpreter) {
        interpreter.push(value);
    }
}
