package org.jmasonry.jvm.types;

public final class MethodDeclaration {
    private final String name;

    public MethodDeclaration(String name) {
        this.name = name;
    }

    public static MethodDeclaration constructor() {
        return new MethodDeclaration("<init>");
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return "()" + Type.unit().getDescriptor();
    }
}
