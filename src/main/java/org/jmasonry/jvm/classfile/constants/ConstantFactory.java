package org.jmasonry.jvm.classfile.constants;

final class ConstantFactory {
    static UTF8Constant utf8Const(String string) {
        return new UTF8Constant(string);
    }

    static ClassConstant classConst(int nameIndex) {
        return new ClassConstant(nameIndex);
    }

    static Constant nameAndType(int nameIndex, int descriptorIndex) {
        return new NameAndType(nameIndex, descriptorIndex);
    }

    static Constant methodRef(int classIndex, int nameAndTypeIndex) {
        return new MethodRef(classIndex, nameAndTypeIndex);
    }
}
