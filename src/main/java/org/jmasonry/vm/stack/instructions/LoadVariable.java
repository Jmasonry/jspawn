package org.jmasonry.vm.stack.instructions;

import org.jmasonry.jvm.types.Type;

final class LoadVariable implements StackInstruction {
   private final String name;
   private final Type   type;

   LoadVariable(String name, Type type) {
      this.name = name;
      this.type = type;
   }

   @Override
   public void interpret(Interpreter interpreter) {
      interpreter.loadVariable(name, type);
   }
}
