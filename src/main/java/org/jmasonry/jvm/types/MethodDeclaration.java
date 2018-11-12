package org.jmasonry.jvm.types;

public final class MethodDeclaration {
   private final String name;
   private final Type   returnType;

   public MethodDeclaration(String name, Type returnType) {
      this.name = name;
      this.returnType = returnType;
   }

   public String getName() {
      return name;
   }

   public String getDescriptor() {
      return "()" + returnType.getDescriptor();
   }
}
