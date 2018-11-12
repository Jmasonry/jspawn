package org.jmasonry.vm.stack.instructions;

import org.jmasonry.vm.values.Value;

final class Push implements StackInstruction {
    private final Value operand;

    Push(Value operand) {
        this.operand = operand;
    }

    @Override
    public void interpret(Interpreter interpreter) {
        interpreter.push(operand);
    }
}
