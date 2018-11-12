package org.jmasonry.jvm.classfile.methods;

enum MethodAccess {
   PUBLIC(0x0001),
   ;

   private final int mask;

   MethodAccess(int mask) {
      this.mask = (short) mask;
   }

   static int mask(MethodAccess... accessModes) {
      var mask = 0;
      for (MethodAccess accessMode : accessModes) {
         mask |= accessMode.mask;
      }
      return mask;
   }
}
