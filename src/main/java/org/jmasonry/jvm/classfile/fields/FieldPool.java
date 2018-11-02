package org.jmasonry.jvm.classfile.fields;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FieldPool implements Iterable<PooledField> {
    private final List<PooledField> fields;

    FieldPool(List<PooledField> fields) {
        this.fields = Collections.unmodifiableList(fields);
    }

    private short getSize() {
        return (short) fields.size();
    }

    @Override
    public Iterator<PooledField> iterator() {
        return fields.iterator();
    }

    public void write(CompilationUnitBuilder builder) {
        builder.appendTwoBytes(getSize());
        for (PooledField constant : this) {
            constant.write(builder);
        }
    }
}
