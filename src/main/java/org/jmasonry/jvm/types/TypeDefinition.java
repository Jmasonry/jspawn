package org.jmasonry.jvm.types;

import java.util.Collections;
import java.util.List;

public class TypeDefinition implements Type {
    private final TypeDeclaration declaration;
    private final List<FieldDeclaration> fields;

    public static TypeDefinition of(TypeDeclaration declaration) {
        return new TypeDefinition(declaration, Collections.emptyList());
    }

    public static TypeDefinition of(TypeDeclaration declaration, List<FieldDeclaration> fields) {
        return new TypeDefinition(declaration, fields);
    }

    private TypeDefinition(TypeDeclaration declaration, List<FieldDeclaration> fields) {
        this.declaration = declaration;
        this.fields = Collections.unmodifiableList(fields);
    }

    public TypeDeclaration getDeclaration() {
        return declaration;
    }

    @Override
    public String getName() {
        return declaration.getSelfType().getName();
    }

    public List<FieldDeclaration> getFields() {
        return fields;
    }
}
