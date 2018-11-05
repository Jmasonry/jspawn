package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.*;
import org.jmasonry.vm.stack.instructions.StackInstructions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.jmasonry.jvm.types.TypeDeclaration.create;

class SimpleClassSpawnTest extends SpawnAbstractTest {
    private static final Type PARENT_TYPE = Type.of(Foo.class);
    private static final TypeDeclaration TYPE_DECLARATION = TypeDeclaration.create(SELF_TYPE, PARENT_TYPE);

    @Test
    void spawns_a_class() {
        // given
        MethodDefinition defaultConstructor = defaultConstructor(PARENT_TYPE);
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, emptyList(), singletonList(defaultConstructor));

        // when
        Class<?> spawned = nest.spawn(definition);

        // then
        assertThat(Foo.class).isAssignableFrom(spawned);
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
        MethodDefinition fooDefinition = new MethodDefinition(
                new MethodDeclaration(methodName),
                new MethodParameters(Collections.singletonList(new Variable("this", SELF_TYPE))),
                Collections.singletonList(StackInstructions.returnTyped(Type.unit())));
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, emptyList(), singletonList(fooDefinition));

        // when
        Class<?> spawned = nest.spawn(definition);

        // then
        Method declaredMethod = spawned.getDeclaredMethod(methodName);
        assertThat(declaredMethod).isNotNull();
    }

    @Test
    void spawns_class_with_default_constructor() throws ReflectiveOperationException {
        // given
        MethodDefinition constructorDefinition = defaultConstructor(PARENT_TYPE);
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, emptyList(), singletonList(constructorDefinition));
        Class<?> spawned = nest.spawn(definition);

        // when
        Object instance = spawned.getDeclaredConstructor().newInstance();

        // then
        assertThat(instance).isInstanceOf(Foo.class);
    }

    // must be public
    public interface IFace {}

    public interface OtherIFace {}

    // all must be public
    public static class Foo {
        public Foo() {
        }
    }
}
