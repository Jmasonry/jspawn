package org.jmasonry.jvm.classfile.fields;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.types.FieldDeclaration;

import java.util.ArrayList;
import java.util.List;

public class FieldPoolBuilder {
    private final ConstantPoolBuilder constants;
    private final List<PooledField> fields = new ArrayList<>();

    public FieldPoolBuilder(ConstantPoolBuilder constants) {
        this.constants = constants;
    }

    public void add(FieldDeclaration field) {
        short nameIndex = constants.appendUTF8(field.getName());
        short typeIndex = constants.appendDescriptor(field.getType());

        PooledField pooledField = new PooledField(nameIndex, typeIndex);
        fields.add(pooledField);
    }

    public FieldPool build() {
        return new FieldPool(fields);
    }
}
