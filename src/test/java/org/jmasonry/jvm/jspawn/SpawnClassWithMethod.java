package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDefinition;
import org.jmasonry.vm.stack.instructions.StackInstruction;
import org.jmasonry.vm.stack.instructions.StackInstructions;
import org.jmasonry.vm.values.Value;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jmasonry.jvm.jspawn.Declarations.typeWithSingleMethod;
import static org.junit.jupiter.params.provider.Arguments.arguments;

final class SpawnClassWithMethod extends SpawnAbstractTest {
    private static final String METHOD_NAME = "foo";

    private static Stream<Arguments> returnedIntegerPrimitives() {
        return Stream.of(
                arguments(Type.of(byte.class), Value.of((byte) 100)),
                arguments(Type.of(byte.class), Value.of((byte) -100)),

                arguments(Type.of(short.class), Value.of((short) -15818)),
                arguments(Type.of(short.class), Value.of((short) 11665)),

                arguments(Type.of(int.class), Value.of(54864665)),
                arguments(Type.of(int.class), Value.of(-464665)),

                arguments(Type.of(long.class), Value.of(0xAAAABBBBCCCCDDDDL)),
                arguments(Type.of(long.class), Value.of(-0xAAAABBBBCCCCDDDDL)),

                arguments(Type.of(float.class), Value.of(0.2f)),
                arguments(Type.of(float.class), Value.of(-0.2f)),

                arguments(Type.of(double.class), Value.of(0.2d)),
                arguments(Type.of(double.class), Value.of(-0.2d))
        );
    }

    @ParameterizedTest
    @MethodSource("returnedIntegerPrimitives")
    void spawn_method_with_primitive_return_type(Type returnedType, Value value) throws ReflectiveOperationException {
        // given
        List<StackInstruction> instructions = Arrays.asList(
                StackInstructions.push(value),
                StackInstructions.returnTyped(returnedType));

        TypeDefinition typeDef = typeWithSingleMethod(METHOD_NAME, returnedType, instructions);

        // when
        Class<?> spawned = nest.spawn(typeDef);

        // then
        ValueComparator result = invoke(spawned);
        assertThat(value.map(result)).isTrue();
    }

    @Test
    void spawns_class_with_a_void_method() throws ReflectiveOperationException {
        // given
        List<StackInstruction> instructions = Collections.singletonList(StackInstructions.returnTyped(Type.unit()));
        TypeDefinition typeDef = typeWithSingleMethod(METHOD_NAME, Type.unit(), instructions);

        // when
        Class<?> spawned = nest.spawn(typeDef);

        // then
        Method declaredMethod = spawned.getDeclaredMethod(METHOD_NAME);
        assertThat(declaredMethod).isNotNull();
    }

    private ValueComparator invoke(Class<?> spawned) throws ReflectiveOperationException {
        Method declaredMethod = spawned.getDeclaredMethod(METHOD_NAME);
        Object instance = spawned.getConstructor().newInstance();

        Number result = (Number) declaredMethod.invoke(instance);
        return new ValueComparator(result);
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
