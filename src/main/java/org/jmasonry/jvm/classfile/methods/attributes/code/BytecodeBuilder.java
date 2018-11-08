package org.jmasonry.jvm.classfile.methods.attributes.code;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.vm.stack.instructions.StackInstruction;

import static org.jmasonry.jvm.instructions.JvmInstructions.*;

final class BytecodeBuilder implements StackInstruction.Interpreter {
    private final ConstantPoolBuilder constants;
    private final LocalVariables localVariables;

    private final Bytecode bytecode = new Bytecode();

    BytecodeBuilder(ConstantPoolBuilder constants, LocalVariables localVariables) {
        this.constants = constants;
        this.localVariables = localVariables;
    }

    Bytecode build() {
        return bytecode;
    }

    @Override
    public void push(int value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void loadVariable(String name, Type type) {
        var localRef = localVariables.indexOf(name);
        bytecode.append(A_LOAD(localRef));
    }

    @Override
    public void call(Type methodOwner, MethodDeclaration methodDeclaration) {
        var methodRef = constants.appendMethod(methodOwner, methodDeclaration);
        bytecode.append(INVOKE_SPECIAL(methodRef));
    }

    @Override
    public void returnTop(Type returnedType) {
        bytecode.append(RETURN_TOP);
    }
}
