package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.FieldDeclaration;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleClassSpawnTest extends SpawnAbstractTest {
    private static final TypeDeclaration TYPE_DECLARATION = TypeDeclaration.create(SELF_TYPE, Type.of(Object.class));

    @Test
    void spawns_simple_class() {
        // given
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION);

        // when
        Class<?> spawnedClass = nest.spawn(definition);

        // then
        assertThat(spawnedClass.getName()).isEqualTo(SELF_TYPE.getName());
    }

    @Test
    void spawns_class_with_a_field() throws NoSuchFieldException {
        // given
        String fieldName = "foo";
        Type fieldType = Type.of(Integer.class);
        FieldDeclaration field = new FieldDeclaration(fieldName, fieldType);
        TypeDefinition definition = TypeDefinition.of(TYPE_DECLARATION, Collections.singletonList(field));

        // when
        Class<?> spawnedClass = nest.spawn(definition);

        // then
        Field declaredField = spawnedClass.getDeclaredField(fieldName);
        assertThat(declaredField.getType().getTypeName()).isEqualTo(fieldType.getName());
    }
}