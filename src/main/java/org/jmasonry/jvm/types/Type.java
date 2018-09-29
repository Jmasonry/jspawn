package org.jmasonry.jvm.types;

public interface Type {
    static Type of(java.lang.reflect.Type type) {
        return type::getTypeName;
    }

    static Type of(String name) {
        return () -> name;
    }

    String getName();

    default String getDescriptor() {
        return TypeName.toDescriptor(getName());
    }

    default String getInternalName() {
        return TypeName.toInternalType(getName());
    }
}
