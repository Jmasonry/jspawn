package org.jmasonry.jvm.classfile.fields;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FieldAccessTest {
    @Test
    void calculates_access_mask() {
        int mask = FieldAccess.mask(FieldAccess.PRIVATE, FieldAccess.FINAL);

        assertThat(mask).isEqualTo(0b10010);
    }
}