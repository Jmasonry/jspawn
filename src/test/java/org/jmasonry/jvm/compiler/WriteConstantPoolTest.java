package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WriteConstantPoolTest {

    @Test
    void constant_pool_size_is_incremented_by_1() throws IOException {
        // given
        int constantAmount = 3;
        ConstantPoolBuilder poolBuilder = createConstantPoolOfSize(constantAmount);

        CompilationUnitBuilder builder = CompilationUnitBuilder.createInMemory();
        WriteConstantPool step = new WriteConstantPool(poolBuilder);

        // when
        step.execute(builder);

        // then
        int poolSize = readConstantPoolSize(builder);
        assertThat(poolSize).isEqualTo(constantAmount + 1);
    }

    private ConstantPoolBuilder createConstantPoolOfSize(int n) {
        ConstantPoolBuilder builder = new ConstantPoolBuilder();
        for (int i = 0; i < n; i++) {
            builder.appendUTF8("UTF8: " + i);
        }
        return builder;
    }

    private short readConstantPoolSize(CompilationUnitBuilder builder) throws IOException {
        byte[] bytes = builder.build();
        DataInputStream inputStream = new DataInputStream(new ByteArrayInputStream(bytes));
        return inputStream.readShort();
    }
}