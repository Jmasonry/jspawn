package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleClassSpawnTest extends SpawnAbstractTest {
    @Test
    void spawns_simple_class() {
        // given
        TypeDeclaration declaration = TypeDeclaration.create(SELF_TYPE, Type.of(Object.class));
        TypeDefinition definition = TypeDefinition.of(declaration);

        // when
        Class<?> spawnedClass = nest.spawn(definition);

        // then
        assertThat(spawnedClass.getName()).isEqualTo(SELF_TYPE.getName());
    }
}