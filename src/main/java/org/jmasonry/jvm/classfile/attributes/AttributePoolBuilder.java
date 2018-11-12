package org.jmasonry.jvm.classfile.attributes;

import java.util.ArrayList;
import java.util.List;

public final class AttributePoolBuilder {
   private final List<PooledAttribute> attributes = new ArrayList<>();

   public void append(PooledAttribute attribute) {
      attributes.add(attribute);
   }

   public AttributePool build() {
      return new AttributePool(attributes);
   }
}
