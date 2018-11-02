package org.jmasonry.jvm.classfile.fields;

enum FieldAccess {
    PRIVATE(0x0002),
    FINAL(0x0010),
    ;

    private final int mask;

    FieldAccess(int mask) {
        this.mask = mask;
    }

    static int mask(FieldAccess... accessModes) {
        int mask = 0;
        for (FieldAccess accessMode : accessModes) {
            mask |= accessMode.mask;
        }
        return mask;
    }
}
