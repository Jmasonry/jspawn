package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public final class ConstantPool implements Iterable<Constant> {
    private final Map<Integer, Constant> pool;

    ConstantPool(Map<Integer, Constant> pool) {this.pool = Collections.unmodifiableMap(pool);}

    private int getSize() {
        return pool.size();
    }

    @Override
    public Iterator<Constant> iterator() {
        return pool.values().iterator();
    }

    public void write(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(getSize() + 1);
        for (Constant constant : this) {
            constant.write(builder);
        }
    }
}
