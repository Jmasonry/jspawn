package org.jmasonry.jvm.types;

public interface Type {
    static Type of(Class<?> type) {
        return new DefaultType(type.getTypeName());
    }

    static Type of(String name) {
        return new DefaultType(name);
    }

    String getName();

    default String getDescriptor() {
        return TypeName.toDescriptor(getName());
    }

    default String getInternalName() {
        return TypeName.toInternalType(getName());
    }
}
