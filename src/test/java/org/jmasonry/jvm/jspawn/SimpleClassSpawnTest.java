package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.FieldDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;
import org.jmasonry.vm.stack.instructions.StackInstruction;
import org.jmasonry.vm.stack.instructions.StackInstructions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.jmasonry.jvm.jspawn.Declarations.*;
import static org.jmasonry.jvm.types.TypeDeclaration.create;

class SimpleClassSpawnTest extends SpawnAbstractTest {
    @Test
    void spawns_a_class() {
        // given
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, emptyList(), emptyList());

        // when
        Class<?> spawned = nest.spawn(definition);

        // then
        assertThat(Declarations.Foo.class).isAssignableFrom(spawned);
    }

    @Test
    void spawns_implementation() {
        // given
        TypeDeclaration declaration = create(SELF_TYPE, PARENT_TYPE, Type.of(IFace.class), Type.of(OtherIFace.class));
        TypeDefinition definition = TypeDefinition.of(declaration);

        // when
        Class<?> spawned = nest.spawn(definition);

        // then
        assertThat(IFace.class).isAssignableFrom(spawned);
        assertThat(OtherIFace.class).isAssignableFrom(spawned);
    }

    @Test
    void spawns_class_with_a_field() throws NoSuchFieldException {
        // given
        String fieldName = "foo";
        Class<Integer> fieldType = Integer.class;
        FieldDeclaration field = new FieldDeclaration(fieldName, Type.of(fieldType));
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, singletonList(field), emptyList());

        // when
        Class<?> spawned = nest.spawn(definition);

        // then
        Field declaredField = spawned.getDeclaredField(fieldName);
        assertThat(declaredField.getType()).isEqualTo(fieldType);
    }

    @Test
    void spawns_class_with_a_method() throws ReflectiveOperationException {
        // given
        String methodName = "foo";
        List<StackInstruction> instructions = Collections.singletonList(StackInstructions.returnTyped(Type.unit()));
        TypeDefinition definition = Declarations.typeWithSingleMethod(methodName, Type.unit(), instructions);

        // when
        Class<?> spawned = nest.spawn(definition);

        // then
        Method declaredMethod = spawned.getDeclaredMethod(methodName);
        assertThat(declaredMethod).isNotNull();
    }

    @Test
    void spawns_class_with_default_constructor() throws ReflectiveOperationException {
        // given
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, emptyList(), singletonList(constructor()));
        Class<?> spawned = nest.spawn(definition);

        // when
        Object instance = spawned.getDeclaredConstructor().newInstance();

        // then
        assertThat(instance).isInstanceOf(Declarations.Foo.class);
    }

    // must be public
    public interface IFace {}

    public interface OtherIFace {}
}
