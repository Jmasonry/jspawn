package org.jmasonry.jvm.types;

public final class MethodDeclaration {
    private final String name;
    private final Type returnType;

    public MethodDeclaration(String name, Type returnType) {
        this.name = name;
        this.returnType = returnType;
    }

    public static MethodDeclaration constructor() {
        return new MethodDeclaration("<init>", Type.unit());
    }

    public String getName() {
        return name;
    }

    public String getDescriptor() {
        return "()" + returnType.getDescriptor();
    }
}
