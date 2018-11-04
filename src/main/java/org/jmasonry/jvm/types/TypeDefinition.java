package org.jmasonry.jvm.types;

import java.util.Collections;
import java.util.List;

public final class TypeDefinition {
    private final TypeDeclaration declaration;
    private final List<FieldDeclaration> fields;
    private final List<MethodDefinition> methods;

    private TypeDefinition(TypeDeclaration declaration, List<FieldDeclaration> fields, List<MethodDefinition> methods) {
        this.declaration = declaration;
        this.fields = Collections.unmodifiableList(fields);
        this.methods = Collections.unmodifiableList(methods);
    }

    public static TypeDefinition of(TypeDeclaration declaration) {
        return new TypeDefinition(declaration, Collections.emptyList(), Collections.emptyList());
    }

    public static TypeDefinition of(TypeDeclaration declaration, List<FieldDeclaration> fields, List<MethodDefinition> methods) {
        return new TypeDefinition(declaration, fields, methods);
    }

    public TypeDeclaration getDeclaration() {
        return declaration;
    }

    public List<FieldDeclaration> getFields() {
        return fields;
    }

    public List<MethodDefinition> getMethods() {
        return methods;
    }
}
