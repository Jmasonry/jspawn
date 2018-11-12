package org.jmasonry.jvm.compiler;

public interface CompilationUnitBuilder {
   static CompilationUnitBuilder createInMemory() {
      return new InMemoryCompilationUnitBuilder();
   }

   void appendOneByte(int oneByte);
   void appendTwoBytes(int twoBytes);
   void appendFourBytes(int fourBytes);
   void appendEightBytes(long fourBytes);
   void appendUTF8(String string);

   byte[] build();
}
