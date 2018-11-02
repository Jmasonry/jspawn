package org.jmasonry.jvm.classfile.fields;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.types.FieldDeclaration;

import java.util.List;

public final class FieldPoolFactory {
    private final ConstantPoolBuilder constants;

    public FieldPoolFactory(ConstantPoolBuilder constants) {
        this.constants = constants;
    }

    public FieldPool create(List<FieldDeclaration> fields) {
        FieldPoolBuilder builder = new FieldPoolBuilder(constants);
        for (FieldDeclaration field : fields) {
            builder.add(field);
        }
        return builder.build();
    }
}
