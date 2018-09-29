package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.types.TypeDefinition;

public interface Compiler {
    byte[] compile(TypeDefinition type);
}
