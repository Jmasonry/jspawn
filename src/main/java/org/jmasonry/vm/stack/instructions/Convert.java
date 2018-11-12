package org.jmasonry.vm.stack.instructions;

import org.jmasonry.jvm.types.Type;

final class Convert implements StackInstruction {
   private final Type sourceType;
   private final Type targetType;

   Convert(Type sourceType, Type targetType) {
      this.sourceType = sourceType;
      this.targetType = targetType;
   }

   @Override
   public void interpret(Interpreter interpreter) {
      interpreter.convert(sourceType, targetType);
   }
}
