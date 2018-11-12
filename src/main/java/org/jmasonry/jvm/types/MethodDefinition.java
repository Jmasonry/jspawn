package org.jmasonry.jvm.types;

import java.util.List;
import org.jmasonry.vm.stack.instructions.StackInstruction;

public final class MethodDefinition {
   private final MethodDeclaration      declaration;
   private final MethodParameters       parameters;
   private final List<StackInstruction> instructions;

   public MethodDefinition(MethodDeclaration declaration, MethodParameters parameters,
       List<StackInstruction> instructions) {
      this.declaration = declaration;
      this.parameters = parameters;
      this.instructions = instructions;
   }

   public MethodDeclaration getDeclaration() {
      return declaration;
   }

   public MethodParameters getParameters() {
      return parameters;
   }

   public List<StackInstruction> getInstructions() {
      return instructions;
   }
}
