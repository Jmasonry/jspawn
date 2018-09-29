package org.jmasonry.jvm.types;

public class UnresolvedType implements Type {
    private final String name;

    public static Type of(String name) {
        return new UnresolvedType(name);
    }

    private UnresolvedType(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}
