package org.jmasonry.jvm.classfile;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.classfile.fields.FieldPool;
import org.jmasonry.jvm.classfile.fields.FieldPoolBuilder;
import org.jmasonry.jvm.types.FieldDeclaration;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;

import java.util.List;

public final class ClassFileFactory {
    private final ClassFileVersion version;

    public ClassFileFactory(ClassFileVersion version) {
        this.version = version;
    }

    public ClassFile create(TypeDefinition typeDefinition) {
        ConstantPoolBuilder constants = new ConstantPoolBuilder();
        ClassFileHeader header = createHeader(constants, typeDefinition.getDeclaration());
        var fields = createFields(constants, typeDefinition.getFields());

        return new ClassFile(version, header, constants.build(), fields);
    }

    private ClassFileHeader createHeader(ConstantPoolBuilder constants, TypeDeclaration declaration) {
        int accessFlags = 0x0020;
        int thisClassIndex = constants.appendClass(declaration.getSelfType());
        int superClassIndex = constants.appendClass(declaration.getSuperClass());

        int[] interfaceIndexes = declaration.getInterfaces().stream()
                .mapToInt(constants::appendClass)
                .toArray();

        return new ClassFileHeader(accessFlags, thisClassIndex, superClassIndex, interfaceIndexes);
    }

    private static FieldPool createFields(ConstantPoolBuilder constants, List<FieldDeclaration> fields) {
        var pool = new FieldPoolBuilder(constants);
        for (FieldDeclaration field : fields) {
            pool.add(field);
        }
        return pool.build();
    }
}
