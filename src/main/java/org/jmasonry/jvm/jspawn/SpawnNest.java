package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.types.TypeDefinition;

class SpawnNest extends ClassLoader {
    private final Compiler compiler;

    SpawnNest(Compiler compiler) {this.compiler = compiler;}

    Class<?> spawn(TypeDefinition type) {
        var bytes = new byte[0];
        bytes = compiler.compile(type);
        return defineClass(type.getName(), bytes, 0, bytes.length);
    }
}
