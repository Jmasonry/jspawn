package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.dsl.ClassDefinition;

class SpawnNest extends ClassLoader {
    private final Compiler compiler;

    SpawnNest(Compiler compiler) {this.compiler = compiler;}

    Class<?> spawn(ClassDefinition definition) {
        var bytes = new byte[0];
        bytes = compiler.compile(definition);
        return defineClass(definition.getName(), bytes, 0, bytes.length);
    }
}
