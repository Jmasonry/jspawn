package org.jmasonry.jvm.classfile.fields;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FieldPool implements Iterable<PooledField> {
    private final List<PooledField> fields;

    FieldPool(List<PooledField> fields) {
        this.fields = Collections.unmodifiableList(fields);
    }

    public short getSize() {
        return (short) fields.size();
    }

    @Override
    public Iterator<PooledField> iterator() {
        return fields.iterator();
    }
}
