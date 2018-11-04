package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.FieldDeclaration;
import org.jmasonry.jvm.types.MethodDefinition;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.jmasonry.jvm.types.TypeDeclaration.create;

class SimpleClassSpawnTest extends SpawnAbstractTest {
    private static final TypeDeclaration TYPE_DECLARATION = TypeDeclaration.create(SELF_TYPE, Type.of(Object.class));

    @Test
    void spawns_a_class() {
        // given
        Type superClass = Type.of(Foo.class);
        TypeDeclaration declaration = TypeDeclaration.create(SELF_TYPE, superClass);
        MethodDefinition defaultConstructor = defaultConstructor(superClass);

        TypeDefinition definition = TypeDefinition.of(declaration, emptyList(), singletonList(defaultConstructor));

        // when
        Class<?> spawned = nest.spawn(definition);

        // then
        assertThat(Foo.class).isAssignableFrom(spawned);
    }

    @Test
    void spawns_implementation() {
        // given
        Type superClass = Type.of(Object.class);
        TypeDeclaration declaration = create(SELF_TYPE, superClass, Type.of(IFace.class), Type.of(OtherIFace.class));
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
        Type fieldType = Type.of(Integer.class);
        FieldDeclaration field = new FieldDeclaration(fieldName, fieldType);
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, singletonList(field), emptyList());

        // when
        Class<?> spawned = nest.spawn(definition);

        // then
        Field declaredField = spawned.getDeclaredField(fieldName);
        assertThat(declaredField.getType()).isEqualTo(Integer.class);
    }

    @Test
    void spawns_class_with_default_constructor() throws ReflectiveOperationException {
        // given
        Type superClass = Type.of(Foo.class);
        TypeDeclaration declaration = create(SELF_TYPE, superClass);
        MethodDefinition constructorDefinition = defaultConstructor(superClass);
        TypeDefinition definition = TypeDefinition.of(declaration, emptyList(), singletonList(constructorDefinition));
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
