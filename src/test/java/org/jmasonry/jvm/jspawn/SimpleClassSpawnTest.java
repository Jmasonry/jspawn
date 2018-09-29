package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.dsl.ClassDefinition;
import org.jmasonry.jvm.types.Type;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SimpleClassSpawnTest extends SpawnAbstractTest {
    @Test
    void spawns_simple_class() {
        // given
        Type superType = Type.of(Object.class);

        // when
        ClassDefinition definition = ClassDefinition.create(SELF_TYPE, superType);
        Class<?> spawnedClass = nest.spawn(definition);

        // then
        assertThat(spawnedClass.getName()).isEqualTo(SELF_TYPE.getName());
    }
}