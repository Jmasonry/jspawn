package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDefinition;
import org.jmasonry.vm.stack.instructions.StackInstruction;
import org.jmasonry.vm.stack.instructions.StackInstructions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jmasonry.jvm.jspawn.Declarations.typeWithSingleMethod;

final class SpawnClassWithMethod extends SpawnAbstractTest {
    private static final String METHOD_NAME = "foo";


    @Test
    void spawn_method_with_int_return_type() throws ReflectiveOperationException {
        // given
        Integer value = 0xFFFFFF;
        Class<?> returnClass = int.class;
        Type returnedType = Type.of(returnClass);

        List<StackInstruction> instructions = Arrays.asList(
                StackInstructions.push(value),
                StackInstructions.returnTyped(returnedType));

        TypeDefinition typeDef = typeWithSingleMethod(METHOD_NAME, returnedType, instructions);

        // when
        Class<?> spawned = nest.spawn(typeDef);

        // then
        Method declaredMethod = spawned.getDeclaredMethod(METHOD_NAME);
        Object instance = spawned.getConstructor().newInstance();

        Object result = declaredMethod.invoke(instance);
        assertThat(result).isEqualTo(value);
    }
}
