package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.*;
import org.jmasonry.vm.stack.instructions.StackInstructions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;

final class SpawnClassWithMethod extends SpawnAbstractTest {
    private static final String METHOD_NAME = "foo";
    private static final Variable THIS = new Variable("this", SELF_TYPE);
    private static final MethodParameters THIS_PARAMETER = new MethodParameters(Collections.singletonList(THIS));

    @Test
    void spawn_method_with_int_return_type() throws ReflectiveOperationException {
        // given
        Integer value = 0xFFFFFF;
        Class<?> returnClass = int.class;

        MethodDeclaration methodDeclaration = new MethodDeclaration(METHOD_NAME, Type.of(returnClass));
        MethodDefinition methodDef = new MethodDefinition(methodDeclaration, THIS_PARAMETER, Arrays.asList(
                StackInstructions.push(value),
                StackInstructions.returnTyped(Type.of(returnClass))));

        TypeDefinition typeDef = TypeDefinition.of(TYPE_DECLARATION, emptyList(), Arrays.asList(
                defaultConstructor(PARENT_TYPE),
                methodDef
        ));
        // when
        Class<?> spawned = nest.spawn(typeDef);

        // then
        Method declaredMethod = spawned.getDeclaredMethod(METHOD_NAME, returnClass);
        Object instance = spawned.getConstructor().newInstance();

        Object result = declaredMethod.invoke(instance);
        assertThat(result).isEqualTo(value);
    }
}
