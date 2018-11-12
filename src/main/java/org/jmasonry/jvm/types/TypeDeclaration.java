package org.jmasonry.jvm.types;


import java.util.Arrays;
import java.util.List;

public final class TypeDeclaration {
   private final Type       selfType;
   private final Type       superClass;
   private final List<Type> interfaces;

   private TypeDeclaration(Type selfType, Type superClass, List<Type> interfaces) {
      this.selfType = selfType;
      this.superClass = superClass;
      this.interfaces = interfaces;
   }

   public static TypeDeclaration create(Type selfType, Type superClass, Type... interfaces) {
      return new TypeDeclaration(selfType, superClass, Arrays.asList(interfaces));
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
