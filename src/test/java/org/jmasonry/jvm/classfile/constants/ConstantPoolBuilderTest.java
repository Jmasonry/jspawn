package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.types.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.jmasonry.jvm.classfile.constants.ConstantFactory.classConst;
import static org.jmasonry.jvm.classfile.constants.ConstantFactory.utf8Const;

class ConstantPoolBuilderTest {
    private ConstantPoolBuilder builder;

    @BeforeEach
    void setUp() {
        builder = new ConstantPoolBuilder();
    }

    @Test
    void appends_utf8() {
        // given
        String string = "a string";

        // when
        builder.appendUTF8(string);

        // then
        ConstantPool constantPool = builder.build();
        assertThat(constantPool).containsExactly(utf8Const(string));
    }

    @Test
    void appends_utf8_before_appending_class() {
        // given
        String typeName = "type name";
        Type type = Type.of(typeName);

        // when
        builder.appendClass(type);

        // then
        ConstantPool constantPool = builder.build();
        assertThat(constantPool).containsExactly(utf8Const(typeName), classConst((short) 1));
    }
}