package org.jmasonry.jvm.classfile.attributes;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public final class AttributePool implements Iterable<PooledAttribute> {
   private final List<PooledAttribute> attributes;

   AttributePool(List<PooledAttribute> attributes) {
      this.attributes = Collections.unmodifiableList(attributes);
   }

   public int size() {
      return attributes.size();
   }

   @Override
   public Iterator<PooledAttribute> iterator() {
      return attributes.iterator();
   }

   public void write(CompilationUnitBuilder builder) {
      builder.appendTwoBytes(size());
      for (PooledAttribute attribute : attributes) {
         attribute.write(builder);
      }
   }
}
