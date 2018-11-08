package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.compiler.DefaultCompiler;

abstract class SpawnAbstractTest {
    private final Compiler compiler = new DefaultCompiler();
    final SpawnNest nest = new SpawnNest(compiler);

}
