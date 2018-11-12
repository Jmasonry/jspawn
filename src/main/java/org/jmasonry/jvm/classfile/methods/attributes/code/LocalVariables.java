package org.jmasonry.jvm.classfile.methods.attributes.code;

import java.util.HashMap;
import java.util.Map;
import org.jmasonry.jvm.types.MethodParameters;

final class LocalVariables {
   private final Map<String, Integer> indices = new HashMap<>();

   static LocalVariables createUsing(MethodParameters parameters) {
      var variables = new LocalVariables();
      for (var parameter : parameters.names()) {
         variables.indexOf(parameter);
      }
      return variables;
   }

   int indexOf(String variable) {
      return indices.computeIfAbsent(variable, $ -> count());
   }

   int count() {
      return indices.size();
   }
}
