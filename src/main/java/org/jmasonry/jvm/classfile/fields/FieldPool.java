package org.jmasonry.jvm.classfile.fields;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public final class FieldPool implements Iterable<PooledField> {
   private final List<PooledField> fields;

   FieldPool(List<PooledField> fields) {
      this.fields = Collections.unmodifiableList(fields);
   }

   private int getSize() {
      return fields.size();
   }

   @Override
   public Iterator<PooledField> iterator() {
      return fields.iterator();
   }

   public void write(CompilationUnitBuilder builder) {
      builder.appendTwoBytes(getSize());
      for (PooledField constant : this) {
         constant.write(builder);
      }
   }
}
