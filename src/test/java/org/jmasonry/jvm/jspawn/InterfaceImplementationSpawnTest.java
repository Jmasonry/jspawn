package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jmasonry.jvm.types.TypeDeclaration.create;

final class InterfaceImplementationSpawnTest extends SpawnAbstractTest {
    @Test
    void spawns_implementation() {
        // given
        Type superClass = Type.of(Object.class);
        TypeDeclaration declaration = create(SELF_TYPE, superClass, Type.of(IFace.class), Type.of(OtherIFace.class));
        TypeDefinition definition = TypeDefinition.of(declaration);

        // when
        Class<?> spawnedClass = nest.spawn(definition);

        // then
        assertThat(IFace.class).isAssignableFrom(spawnedClass);
        assertThat(OtherIFace.class).isAssignableFrom(spawnedClass);
    }

    // both must be public
    public interface IFace {}

    public interface OtherIFace {}
}
