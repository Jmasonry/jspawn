package org.jmasonry.jvm;

public interface Type {
    static Type of(String name) {
        return () -> name;
    }

    static Type of(Class<?> type) {
        return type::getName;
    }

    String getName();
}
