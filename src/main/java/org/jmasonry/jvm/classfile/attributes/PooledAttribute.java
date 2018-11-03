package org.jmasonry.jvm.classfile.attributes;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public interface PooledAttribute {
    void write(CompilationUnitBuilder builder);
}
