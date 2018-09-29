package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.compiler.DefaultCompiler;
import org.jmasonry.jvm.types.Type;

abstract class SpawnAbstractTest {
    static final Type SELF_TYPE = Type.of("org.jmasonry.jvm.jspawn.Bar");

    private final Compiler compiler = new DefaultCompiler();
    final SpawnNest nest = new SpawnNest(compiler);
}
