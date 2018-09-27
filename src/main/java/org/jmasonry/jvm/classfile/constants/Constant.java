package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public interface Constant {
    void write(CompilationUnitBuilder output);
}
