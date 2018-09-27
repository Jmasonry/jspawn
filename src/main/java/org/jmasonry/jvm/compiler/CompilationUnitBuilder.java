package org.jmasonry.jvm.compiler;

public interface CompilationUnitBuilder {
    void appendOneByte(int oneByte);

    void appendTwoBytes(int twoBytes);

    void appendFourBytes(int fourBytes);

    void appendUTF8(String string);

    byte[] build();
}
