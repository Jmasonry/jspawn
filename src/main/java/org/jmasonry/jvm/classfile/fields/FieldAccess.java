package org.jmasonry.jvm.classfile.fields;

enum FieldAccess {
    PRIVATE(0x0002),
    FINAL(0x0010),
    ;

    private final short mask;

    FieldAccess(int mask) {
        this.mask = (short) mask;
    }

    static short mask(FieldAccess... accessModes) {
        short mask = 0;
        for (FieldAccess accessMode : accessModes) {
            mask |= accessMode.mask;
        }
        return mask;
    }
}
