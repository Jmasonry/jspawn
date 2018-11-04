package org.jmasonry.vm.stack.instructions;

import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;

final class Call implements StackInstruction {
    private final Type methodOwner;
    private final MethodDeclaration methodDeclaration;

    Call(Type methodOwner, MethodDeclaration methodDeclaration) {
        this.methodOwner = methodOwner;
        this.methodDeclaration = methodDeclaration;
    }

    @Override
    public void interpret(Interpreter interpreter) {
        interpreter.call(methodOwner, methodDeclaration);
    }
}
