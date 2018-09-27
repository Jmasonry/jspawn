package org.jmasonry.jvm.classfile.constants;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

public final class ConstantPool implements Iterable<Constant> {
    private final Map<Short, Constant> pool;

    ConstantPool(Map<Short, Constant> pool) {this.pool = Collections.unmodifiableMap(pool);}

    public short getSize() {
        return (short) pool.size();
    }

    @Override
    public Iterator<Constant> iterator() {
        return pool.values().iterator();
    }
}
