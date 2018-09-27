package org.jmasonry.jvm.dsl;

import org.jmasonry.jvm.Type;

import java.util.Arrays;
import java.util.List;

public final class ClassDefinition {
    private final Type selfType;
    private final Type superClass;
    private final List<Type> interfaces;

    private ClassDefinition(Type selfType, Type superClass, List<Type> interfaces) {
        this.selfType = selfType;
        this.superClass = superClass;
        this.interfaces = interfaces;
    }

    public static ClassDefinition create(Type selfType, Type superClass, Type... interfaces) {
        return new ClassDefinition(selfType, superClass, Arrays.asList(interfaces));
    }

    public String getName() {
        return selfType.getName();
    }

    public Type getSelfType() {
        return selfType;
    }

    public Type getSuperClass() {
        return superClass;
    }

    public List<Type> getInterfaces() {
        return interfaces;
    }
}
