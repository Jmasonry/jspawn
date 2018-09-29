package org.jmasonry.jvm.types;


public class TypeDefinition implements Type {
    private final TypeDeclaration declaration;

    public static TypeDefinition of(TypeDeclaration declaration) {
        return new TypeDefinition(declaration);
    }

    private TypeDefinition(TypeDeclaration declaration) {
        this.declaration = declaration;
    }

    public TypeDeclaration getDeclaration() {
        return declaration;
    }

    @Override
    public String getName() {
        return declaration.getSelfType().getName();
    }
}
