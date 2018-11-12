package org.jmasonry.jvm.types;

final class TypeName {
   static String toDescriptor(String name) {
      switch (name) {
         case "byte": return "B";
         case "short": return "S";
         case "int": return "I";
         case "long": return "J";
         case "float": return "F";
         case "double": return "D";
         case "char": return "C";
         case "boolean": return "Z";
         default:
            int expectedArrayIndex = name.length() - 2;
            if (name.charAt(expectedArrayIndex) == '[') {
               String arrayElement = name.substring(0, expectedArrayIndex);
               return "[" + toDescriptor(arrayElement);
            } else {
               return "L" + toInternalType(name) + ";";
            }
      }
   }

   static String toInternalType(String name) {
      return name.replace('.', '/');
   }
}
