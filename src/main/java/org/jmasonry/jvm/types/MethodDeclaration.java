package org.jmasonry.jvm.types;

public final class MethodDeclaration {
    public String getName() {
        return "<init>";
    }

    public String getDescriptor() {
        return "()" + Type.unit().getDescriptor();
    }
}
