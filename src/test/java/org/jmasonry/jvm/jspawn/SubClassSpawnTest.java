package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.compiler.DefaultCompiler;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class SubClassSpawnTest extends SpawnAbstractTest {
    private final Compiler compiler = new DefaultCompiler();
    private final SpawnNest nest = new SpawnNest(compiler);

    @Test
    void spawns_subclass() {
        // given
        TypeDeclaration declaration = TypeDeclaration.create(SELF_TYPE, Type.of(Foo.class));
        TypeDefinition definition = TypeDefinition.of(declaration);

        // when
        Class<?> spawnedClass = nest.spawn(definition);

        // then
        assertThat(Foo.class).isAssignableFrom(spawnedClass);
    }

    // must be public
    public class Foo {}
}
