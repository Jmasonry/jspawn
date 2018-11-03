package org.jmasonry.jvm.classfile.methods;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MethodPool implements Iterable<PooledMethod> {
    private final List<PooledMethod> methods;

    MethodPool(List<PooledMethod> methods) {
        this.methods = Collections.unmodifiableList(methods);
    }

    public int getSize() {
        return methods.size();
    }

    @Override
    public Iterator<PooledMethod> iterator() {
        return methods.iterator();
    }


    public void write(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(getSize());
        for (var method : this) {
            method.write(builder);
        }
    }
}
