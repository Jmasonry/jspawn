package org.jmasonry.jvm.types;

public final class FieldDeclaration {
   private final String name;
   private final Type   type;

   public FieldDeclaration(String name, Type type) {
      this.name = name;
      this.type = type;
   }

   public String getName() {
      return name;
   }

   public Type getType() {
      return type;
   }
}
