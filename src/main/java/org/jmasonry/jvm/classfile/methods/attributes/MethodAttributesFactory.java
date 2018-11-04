package org.jmasonry.jvm.classfile.methods.attributes;

import org.jmasonry.jvm.classfile.attributes.AttributePool;
import org.jmasonry.jvm.classfile.attributes.AttributePoolBuilder;
import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.classfile.methods.attributes.code.Code;
import org.jmasonry.jvm.classfile.methods.attributes.code.CodeFactory;
import org.jmasonry.jvm.types.MethodDefinition;

public final class MethodAttributesFactory {
    private final CodeFactory codeFactory;

    public MethodAttributesFactory(ConstantPoolBuilder constants) {
        this.codeFactory = new CodeFactory(constants);
    }

    public AttributePool create(MethodDefinition definition) {
        var attributes = new AttributePoolBuilder();
        attributes.append(code(definition));

        return attributes.build();
    }

    private Code code(MethodDefinition definition) {
        return codeFactory.create(definition.getParameters(), definition.getInstructions());
    }
}
