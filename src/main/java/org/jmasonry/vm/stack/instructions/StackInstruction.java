package org.jmasonry.vm.stack.instructions;

import java.util.List;
import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.vm.values.Value;

public interface StackInstruction {
   void interpret(Interpreter interpreter);

   interface Interpreter {
      static Interpreter aggregate(List<Interpreter> interpreters) {
         return new AggregatedInterpreter(interpreters);
      }

      void push(Value operand);
      void loadVariable(String name, Type type);
      void call(Type methodOwner, MethodDeclaration methodDeclaration);
      void returnTop(Type returnedType);
   }
}
