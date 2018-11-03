package org.jmasonry.jvm.classfile.fields;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.types.FieldDeclaration;

import java.util.ArrayList;
import java.util.List;

public final class FieldPoolBuilder {
    private final ConstantPoolBuilder constants;
    private final List<PooledField> fields = new ArrayList<>();

    public FieldPoolBuilder(ConstantPoolBuilder constants) {
        this.constants = constants;
    }

    public void add(FieldDeclaration field) {
        PooledField pooledField = create(field);
        fields.add(pooledField);
    }

    private PooledField create(FieldDeclaration field) {
        var nameIndex = constants.appendUTF8(field.getName());
        var typeIndex = constants.appendUTF8(field.getType().getDescriptor());

        return new PooledField(nameIndex, typeIndex);
    }

    public FieldPool build() {
        return new FieldPool(fields);
    }
}
