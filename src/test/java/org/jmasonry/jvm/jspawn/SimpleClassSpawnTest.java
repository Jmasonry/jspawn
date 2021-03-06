package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.jspawn.types.Foo;
import org.jmasonry.jvm.jspawn.types.IFace;
import org.jmasonry.jvm.jspawn.types.OtherIFace;
import org.jmasonry.jvm.types.FieldDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

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
    void spawns_class_with_default_constructor() throws ReflectiveOperationException {
        // given
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, emptyList(), singletonList(constructor()));
        Class<?> spawned = nest.spawn(definition);

        // when
        Object instance = spawned.getDeclaredConstructor().newInstance();

        // then
        assertThat(instance).isInstanceOf(Foo.class);
    }
}
