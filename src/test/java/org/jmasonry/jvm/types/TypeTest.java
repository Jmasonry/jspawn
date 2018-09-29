package org.jmasonry.jvm.types;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class TypeTest {
    private static Stream<Arguments> type_descriptors() {
        return Stream.of(
                arguments(Type.of("byte"), "B"),
                arguments(Type.of("short"), "S"),
                arguments(Type.of("int"), "I"),
                arguments(Type.of("long"), "J"),
                arguments(Type.of("float"), "F"),
                arguments(Type.of("double"), "D"),
                arguments(Type.of("char"), "C"),
                arguments(Type.of("boolean"), "Z"),
                arguments(Type.of(Object.class), "Ljava/lang/Object;"),
                arguments(Type.of(int[].class), "[I"),
                arguments(Type.of(int[][].class), "[[I"),
                arguments(Type.of(Object[][][].class), "[[[Ljava/lang/Object;")
        );
    }

    @ParameterizedTest
    @MethodSource("type_descriptors")
    void get_descriptor(Type type, String expectedDescriptor) {
        String descriptor = type.getDescriptor();

        assertThat(descriptor).isEqualTo(expectedDescriptor);
    }

    private static Stream<Arguments> internal_names() {
        return Stream.of(
                arguments(Type.of(Object.class), "java/lang/Object"),
                arguments(Type.of("org.jmasonry.jvm.Foo"), "org/jmasonry/jvm/Foo")
        );
    }

    @ParameterizedTest
    @MethodSource("internal_names")
    void get_internal_name(Type type, String expectedDescriptor) {
        String descriptor = type.getInternalName();

        assertThat(descriptor).isEqualTo(expectedDescriptor);
    }

}