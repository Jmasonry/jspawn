package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.types.TypeDefinition;

class SpawnNest extends ClassLoader {
    private final Compiler compiler;

    SpawnNest(Compiler compiler) {this.compiler = compiler;}

    Class<?> spawn(TypeDefinition type) {
        var bytes = compiler.compile(type);
        String typeName = type.getDeclaration().getName();
        return defineClass(typeName, bytes, 0, bytes.length);
    }
}
