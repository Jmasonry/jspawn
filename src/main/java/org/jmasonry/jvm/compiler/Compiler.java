package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.dsl.ClassDefinition;

public interface Compiler {
    byte[] compile(ClassDefinition definition);
}
