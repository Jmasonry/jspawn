package org.jmasonry.jvm.classfile;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

public enum ClassFileVersion {
   JAVA_10(53, 0);

   private final int major;
   private final int minor;

   ClassFileVersion(int major, int minor) {
      this.major = major;
      this.minor = minor;
   }

   public void write(CompilationUnitBuilder builder) {
      builder.appendTwoBytes(minor);
      builder.appendTwoBytes(major);
   }
}
