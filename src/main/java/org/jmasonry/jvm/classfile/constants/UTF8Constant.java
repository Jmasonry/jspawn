package org.jmasonry.jvm.classfile.constants;

import org.jmasonry.jvm.compiler.CompilationUnitBuilder;

import java.util.Objects;

final class UTF8Constant implements Constant {
    private final String string;

    UTF8Constant(String string) {this.string = string;}

    @Override
    public void write(CompilationUnitBuilder builder) {
        builder.appendOneByte(1);
        builder.appendUTF8(string);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof UTF8Constant)) {
            return false;
        }
        UTF8Constant utf8 = (UTF8Constant) object;
        return Objects.equals(string, utf8.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }
}
