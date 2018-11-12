package org.jmasonry.vm.stack.instructions;

import java.util.List;
import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.vm.values.Value;

final class AggregatedInterpreter implements StackInstruction.Interpreter {
   private final List<StackInstruction.Interpreter> interpreters;

   AggregatedInterpreter(List<StackInstruction.Interpreter> interpreters) {
      this.interpreters = interpreters;
   }

   @Override
   public void push(Value operand) {
      for (StackInstruction.Interpreter interpreter : interpreters) {
         interpreter.push(operand);
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
