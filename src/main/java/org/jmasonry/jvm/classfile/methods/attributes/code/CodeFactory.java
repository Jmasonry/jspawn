package org.jmasonry.jvm.classfile.methods.attributes.code;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.types.MethodParameters;
import org.jmasonry.vm.stack.instructions.StackInstruction;
import org.jmasonry.vm.stack.instructions.StackInstruction.Interpreter;

import java.util.Arrays;
import java.util.List;

public final class CodeFactory {
    private final ConstantPoolBuilder constants;

    public CodeFactory(ConstantPoolBuilder constants) {
        this.constants = constants;
    }

    public Code create(MethodParameters parameters, List<StackInstruction> instructions) {
        var stackSizeTracker = new StackSizeTracker();
        var localVariables = LocalVariables.createUsing(parameters);
        var bytecodeBuilder = new BytecodeBuilder(constants, localVariables);

        Interpreter interpreter = Interpreter.aggregate(Arrays.asList(stackSizeTracker, bytecodeBuilder));
        for (StackInstruction instruction : instructions) {
            instruction.interpret(interpreter);
        }

        return assemble(stackSizeTracker, localVariables, bytecodeBuilder.build());
    }

    private Code assemble(StackSizeTracker stackSizeTracker, LocalVariables localVariables, Bytecode bytecode) {
        var nameIndex = constants.appendUTF8("Code");
        return new Code(nameIndex, stackSizeTracker.maxStackSize(), localVariables.count(), bytecode);
    }
}
