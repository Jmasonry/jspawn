package org.jmasonry.jvm.classfile.methods.attributes.code;

import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.vm.stack.instructions.StackInstruction;

final class StackSizeTracker implements StackInstruction.Interpreter {
    private int currentStackSize = 0;
    private int maximalStackSize = 0;

    int maxStackSize() {
        return maximalStackSize;
    }

    @Override
    public void loadVariable(String name, Type type) {
        extendBy(1);
    }

    @Override
    public void call(Type methodOwner, MethodDeclaration methodDeclaration) {}

    @Override
    public void returnTop(Type returnedType) {}

    private void extendBy(int size) {
        currentStackSize += size;
        maximalStackSize = Math.max(maximalStackSize, currentStackSize);
    }
}
