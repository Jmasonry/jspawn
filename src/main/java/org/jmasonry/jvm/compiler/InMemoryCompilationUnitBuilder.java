package org.jmasonry.jvm.compiler;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

final class InMemoryCompilationUnitBuilder implements CompilationUnitBuilder {
    public static final String EXCEPTION_MESSAGE = "Could not append to the in memory array";
    private final ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    private final DataOutputStream outputStream = new DataOutputStream(bytes);

    @Override
    public void appendOneByte(int oneByte) {
        try {
            outputStream.writeByte(oneByte);
        } catch (IOException e) {
            throw createException(e);
        }
    }

    @Override
    public void appendTwoBytes(int twoBytes) {
        try {
            outputStream.writeShort(twoBytes);
        } catch (IOException e) {
            throw createException(e);
        }
    }

    @Override
    public void appendFourBytes(int fourBytes) {
        try {
            outputStream.writeInt(fourBytes);
        } catch (IOException e) {
            throw createException(e);
        }
    }

    @Override
    public void appendUTF8(String string) {
        try {
            outputStream.writeUTF(string);
        } catch (IOException e) {
            throw createException(e);
        }
    }

    private CompilationException createException(IOException e) {
        return new CompilationException(EXCEPTION_MESSAGE, e);
    }

    @Override
    public byte[] build() {
        try {
            outputStream.flush();
            return bytes.toByteArray();
        } catch (IOException e) {
            throw createException(e);
        }
    }
}
