package org.jmasonry.jvm.classfile;


enum ClassAccess {
    PUBLIC(0x0001),
    SUPER(0x0020),
    ;
    private final int mask;

    ClassAccess(int mask) {
        this.mask = mask;
    }

    static int mask(ClassAccess... accessModes) {
        int mask = 0;
        for (ClassAccess accessMode : accessModes) {
            mask |= accessMode.mask;
        }
        return mask;
    }
}
