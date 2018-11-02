package org.jmasonry.jvm.classfile.constants;


import org.jmasonry.jvm.types.Type;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.jmasonry.jvm.classfile.constants.ConstantFactory.classConst;
import static org.jmasonry.jvm.classfile.constants.ConstantFactory.utf8Const;

public final class ConstantPoolBuilder {
    private final Map<Constant, Integer> cache = new LinkedHashMap<>();

    public int appendUTF8(String string) {
        var constant = utf8Const(string);
        return getOrCreate(constant);
    }

    public int appendDescriptor(Type type) {
        String descriptor = type.getDescriptor();
        return appendUTF8(descriptor);
    }

    public int appendClass(Type type) {
        String typeName = type.getInternalName();
        var nameIndex = appendUTF8(typeName);

        var constant = classConst(nameIndex);
        return getOrCreate(constant);
    }

    public ConstantPool build() {
        var constants = new LinkedHashMap<Integer, Constant>();
        for (var entry : cache.entrySet()) {
            constants.put(entry.getValue(), entry.getKey());
        }
        return new ConstantPool(constants);
    }

    private Integer getOrCreate(Constant constant) {
        return cache.computeIfAbsent(constant, k -> offset());
    }

    private int offset() {
        return cache.size() + 1;
    }
}