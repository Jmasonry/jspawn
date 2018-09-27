package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.Type;
import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.compiler.DefaultCompiler;
import org.jmasonry.jvm.dsl.ClassDefinition;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

final class SubClassSpawnTest extends SpawnAbstractTest {
    private final Compiler compiler = new DefaultCompiler();
    private final SpawnNest nest = new SpawnNest(compiler);

    @Test
    void spawns_subclass() {
        // given
        Type superType = Type.of(Foo.class);

        // when
        ClassDefinition definition = ClassDefinition.create(SELF_TYPE, superType);
        Class<?> spawnedClass = nest.spawn(definition);

        // then
        assertThat(Foo.class).isAssignableFrom(spawnedClass);
    }

    // must be public
    public class Foo {}
}
