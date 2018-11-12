package org.jmasonry.jvm.classfile.methods.attributes.code;

import org.jmasonry.jvm.types.JvmTypes;
import org.jmasonry.jvm.types.MethodDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.values.Precision;
import org.jmasonry.vm.stack.instructions.StackInstruction;
import org.jmasonry.vm.values.Value;

final class StackSizeTracker implements StackInstruction.Interpreter {
   private int currentStackSize = 0;
   private int maximalStackSize = 0;

   int maxStackSize() {
      return maximalStackSize;
   }

   @Override
   public void push(Value operand) {
      Precision precision = Precision.of(operand);
      int operandCount = operandCount(precision);
      extendBy(operandCount);
   }

   @Override
   public void loadVariable(String name, Type type) {
      var pushedOperands = operandCount(JvmTypes.precisionOf(type));
      extendBy(pushedOperands);
   }

   @Override
   public void call(Type methodOwner, MethodDeclaration methodDeclaration) {}

   @Override
   public void returnTop(Type returnedType) {}

   @Override
   public void convert(Type sourceType, Type targetType) {
      var poppedOperands = operandCount(JvmTypes.precisionOf(sourceType));
      var pushedOperands = operandCount(JvmTypes.precisionOf(targetType));
      extendBy(pushedOperands - poppedOperands);
   }

   private void extendBy(int size) {
      currentStackSize += size;
      maximalStackSize = Math.max(maximalStackSize, currentStackSize);
   }

   private int operandCount(Precision precision) {
      return precision == Precision.EIGHT_BYTES
          ? 2
          : 1;
   }
}
