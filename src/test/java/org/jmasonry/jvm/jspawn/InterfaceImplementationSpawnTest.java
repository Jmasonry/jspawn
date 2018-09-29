package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.dsl.ClassDefinition;
import org.jmasonry.jvm.types.Type;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class InterfaceImplementationSpawnTest extends SpawnAbstractTest {
    @Test
    void spawns_implementation() {
        // given
        Type superType = Type.of(Object.class);
        Type ifaceType = Type.of(IFace.class);
        Type otherIfaceType = Type.of(OtherIFace.class);

        // when
        ClassDefinition definition = ClassDefinition.create(SELF_TYPE, superType, ifaceType, otherIfaceType);
        Class<?> spawnedClass = nest.spawn(definition);

        // then
        assertThat(IFace.class).isAssignableFrom(spawnedClass);
        assertThat(OtherIFace.class).isAssignableFrom(spawnedClass);
    }

    // both must be public
    public interface IFace {}

    public interface OtherIFace {}
}
