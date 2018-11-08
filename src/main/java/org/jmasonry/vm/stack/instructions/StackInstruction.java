package org.jmasonry.vm.stack.instructions;

import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;

import java.util.List;

public interface StackInstruction {
    void interpret(Interpreter interpreter);

    interface Interpreter {
        static Interpreter aggregate(List<Interpreter> interpreters) {
            return new AggregatedInterpreter(interpreters);
        }

        void push(int value);

        void loadVariable(String name, Type type);

        void call(Type methodOwner, MethodDeclaration methodDeclaration);

        void returnTop(Type returnedType);
    }
}
