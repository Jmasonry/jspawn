package org.jmasonry.jvm.jspawn;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jmasonry.jvm.jspawn.Declarations.typeWithSingleMethod;
import static org.jmasonry.vm.stack.instructions.StackInstructions.convert;
import static org.jmasonry.vm.stack.instructions.StackInstructions.push;
import static org.jmasonry.vm.stack.instructions.StackInstructions.returnTyped;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDefinition;
import org.jmasonry.vm.stack.instructions.StackInstruction;
import org.jmasonry.vm.values.Value;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

final class PrimitiveConversionTest extends SpawnAbstractTest {
   private static final String METHOD_NAME = "foo";

   private static final Type aByte   = Type.of(byte.class);
   private static final Type aChar   = Type.of(char.class);
   private static final Type aShort  = Type.of(short.class);
   private static final Type anInt   = Type.of(int.class);
   private static final Type aLong   = Type.of(long.class);
   private static final Type aFloat  = Type.of(float.class);
   private static final Type aDouble = Type.of(double.class);

   private static Stream<Arguments> conversions() {
      // @formatter:off
      return Stream.of(
          arguments(anInt, aLong,   Value.of(0xFFFFFF), Value.of((long) 0xFFFFFF)),
          arguments(anInt, aFloat,  Value.of(0xFFFFFF), Value.of((float) 0xFFFFFF)),
          arguments(anInt, aDouble, Value.of(0xFFFFFF), Value.of((double) 0xFFFFFF)),

          arguments(aLong, anInt,   Value.of(0xFFFFFFL), Value.of((int) 0xFFFFFFL)),
          arguments(aLong, aFloat,  Value.of(0xFFFFFFL), Value.of((float) 0xFFFFFFL)),
          arguments(aLong, aDouble, Value.of(0xFFFFFFL), Value.of((double) 0xFFFFFFL)),

          arguments(aFloat, anInt,   Value.of(0.2F), Value.of((int) 0.2F)),
          arguments(aFloat, aLong,   Value.of(0.2F), Value.of((long) 0.2F)),
          arguments(aFloat, aDouble, Value.of(0.2F), Value.of((double) 0.2F)),

          arguments(aDouble, anInt,  Value.of(0.2D), Value.of((int) 0.2D)),
          arguments(aDouble, aLong,  Value.of(0.2D), Value.of((long) 0.2D)),
          arguments(aDouble, aFloat, Value.of(0.2D), Value.of((float) 0.2D)),

          arguments(anInt, aByte,  Value.of(0xFFFFFF), Value.of((byte) 0xFFFFFF)),
          arguments(anInt, aChar,  Value.of(0xFFFFFF), Value.of((char) 0xFFFFFF)),
          arguments(anInt, aShort, Value.of(0xFFFFFF), Value.of((short) 0xFFFFFF))
      );
      // @formatter:on
   }

   @ParameterizedTest
   @MethodSource("conversions")
   void convert_primitive_operands(Type sourceType, Type targetType, Value value, Value expectedValue)
       throws ReflectiveOperationException {
      // given
      List<StackInstruction> instructions = Arrays.asList(
          push(value), convert(sourceType, targetType), returnTyped(targetType)

      );

      TypeDefinition typeDef = typeWithSingleMethod(METHOD_NAME, targetType, instructions);

      // when
      Class<?> spawned = nest.spawn(typeDef);

      // then
      ValueComparator result = invoke(spawned);
      assertThat(expectedValue.map(result)).isTrue();
   }

   private ValueComparator invoke(Class<?> spawned) throws ReflectiveOperationException {
      Method declaredMethod = spawned.getDeclaredMethod(METHOD_NAME);
      Object instance = spawned.getConstructor().newInstance();

      Object result = declaredMethod.invoke(instance);
      if (result instanceof Character) {
         char c = (Character) result;
         return new ValueComparator((int) c);
      }
      return new ValueComparator((Number) result);
   }

   private static class ValueComparator implements Value.Mapper<Boolean> {
      private final Number number;

      private ValueComparator(Number number) {
         this.number = number;
      }

      @Override
      public Boolean map(int value) {
         return number.intValue() == value;
      }

      @Override
      public Boolean map(long value) {
         return number.longValue() == value;
      }

      @Override
      public Boolean map(float value) {
         return number.floatValue() == value;
      }

      @Override
      public Boolean map(double value) {
         return number.doubleValue() == value;
      }
   }
}
