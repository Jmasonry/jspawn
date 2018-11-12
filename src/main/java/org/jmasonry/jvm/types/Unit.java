package org.jmasonry.jvm.types;

public final class Unit implements Type {
   @Override
   public String getName() {
      return "Unit";
   }

   @Override
   public String getDescriptor() {
      return "V";
   }

   @Override
   public String getInternalName() {
      return "V";
   }
}
