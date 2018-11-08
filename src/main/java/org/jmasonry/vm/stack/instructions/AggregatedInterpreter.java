package org.jmasonry.vm.stack.instructions;

import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;

import java.util.List;

final class AggregatedInterpreter implements StackInstruction.Interpreter {
    private final List<StackInstruction.Interpreter> interpreters;

    AggregatedInterpreter(List<StackInstruction.Interpreter> interpreters) {
        this.interpreters = interpreters;
    }

    @Override
    public void push(int value) {
        for (StackInstruction.Interpreter interpreter : interpreters) {
            interpreter.push(value);
        }
    }

    @Override
    public void loadVariable(String name, Type type) {
        for (StackInstruction.Interpreter interpreter : interpreters) {
            interpreter.loadVariable(name, type);
        }
    }

    @Override
    public void call(Type methodOwner, MethodDeclaration methodDeclaration) {
        for (StackInstruction.Interpreter interpreter : interpreters) {
            interpreter.call(methodOwner, methodDeclaration);
        }
    }

    @Override
    public void returnTop(Type returnedType) {
        for (StackInstruction.Interpreter interpreter : interpreters) {
            interpreter.returnTop(returnedType);
        }
    }
}
