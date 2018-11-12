package org.jmasonry.jvm.classfile.methods.attributes.code;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.instructions.JvmInstruction;
import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.values.Precision;
import org.jmasonry.vm.stack.instructions.StackInstruction;
import org.jmasonry.vm.values.Value;

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
    public void push(Value operand) {
        JvmInstruction instruction;

        Precision precision = Precision.of(operand);
        if (precision.bytes() < 4) {
            instruction = pushValue(operand);
        } else {
            var constantIndex = constants.appendValue(operand);
            instruction = loadConstant(constantIndex, precision);
        }

        bytecode.append(instruction);
    }

    @Override
    public void loadVariable(String name, Type type) {
        var localRef = localVariables.indexOf(name);
        bytecode.append(loadReference(localRef));
    }

    @Override
    public void call(Type methodOwner, MethodDeclaration methodDeclaration) {
        var methodRef = constants.appendMethod(methodOwner, methodDeclaration);
        bytecode.append(invokeSpecial(methodRef));
    }

    @Override
    public void returnTop(Type returnedType) {
        bytecode.append(returnValueOfType(returnedType));
    }
}
