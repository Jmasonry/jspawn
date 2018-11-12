package org.jmasonry.vm.stack.instructions;

import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.vm.values.Value;

public final class StackInstructions {
   public static StackInstruction push(Value value) {
      return new Push(value);
   }

   public static StackInstruction loadLocal(String variable, Type variableType) {
      return new LoadVariable(variable, variableType);
   }

   public static StackInstruction call(Type methodOwner, MethodDeclaration declaration) {
      return new Call(methodOwner, declaration);
   }

   public static StackInstruction returnTyped(Type valueType) {
      return new Return(valueType);
   }
}
