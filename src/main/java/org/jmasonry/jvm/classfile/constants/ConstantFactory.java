package org.jmasonry.jvm.classfile.constants;

final class ConstantFactory {
    static UTF8Constant utf8Const(String string) {
        return new UTF8Constant(string);
    }

    static ClassConstant classConst(short nameIndex) {
        return new ClassConstant(nameIndex);
    }
}
