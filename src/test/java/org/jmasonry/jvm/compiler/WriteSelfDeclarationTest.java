package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.Type;
import org.jmasonry.jvm.dsl.ClassDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.times;
import static org.mockito.MockitoAnnotations.initMocks;

class WriteSelfDeclarationTest {
    private static final Type SELF_TYPE = Type.of("self");
    private static final Type SUPER_TYPE = Type.of(Object.class);
    private static final Type FOO_TYPE = Type.of(Foo.class);
    private static final Type BAR_TYPE = Type.of(Bar.class);

    @Mock
    CompilationUnitBuilder builder;
    InOrder inOrder;

    @BeforeEach
    void setUp() {
        initMocks(this);
        inOrder = inOrder(builder);
    }

    @Test
    void self_type_has_offset_of_2() {
        // given
        ClassDefinition classDefinition = ClassDefinition.create(SELF_TYPE, SUPER_TYPE);
        WriteSelfDeclaration step = new WriteSelfDeclaration(classDefinition);

        // when
        step.execute(builder);

        // then
        inOrder.verify(builder).appendTwoBytes(2);
        inOrder.verify(builder, times(2)).appendTwoBytes(anyInt());             // super_type and interface count = 0
    }

    @Test
    void self_type_has_offset_of_4() {
        // given
        ClassDefinition classDefinition = ClassDefinition.create(SELF_TYPE, SUPER_TYPE);
        WriteSelfDeclaration step = new WriteSelfDeclaration(classDefinition);

        // when
        step.execute(builder);

        // then
        inOrder.verify(builder).appendTwoBytes(4);
        inOrder.verify(builder).appendTwoBytes(anyInt());                       // interface count = 0
    }

    @Test
    void interface_offsets_start_at_6() {
        // given
        ClassDefinition classDefinition = ClassDefinition.create(SELF_TYPE, SUPER_TYPE, FOO_TYPE, BAR_TYPE);
        WriteSelfDeclaration step = new WriteSelfDeclaration(classDefinition);

        // when
        step.execute(builder);

        // then
        inOrder.verify(builder).appendTwoBytes(4);                              // super class
        inOrder.verify(builder).appendTwoBytes(2);                              // interface count = 2 (Foo, Bar)
        inOrder.verify(builder).appendTwoBytes(6);                              // first interface
        inOrder.verify(builder).appendTwoBytes(8);                              // second interface
    }

    interface Foo {}

    interface Bar {}
}