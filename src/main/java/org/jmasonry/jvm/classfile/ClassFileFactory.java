package org.jmasonry.jvm.classfile;

import org.jmasonry.jvm.classfile.constants.ConstantPoolBuilder;
import org.jmasonry.jvm.classfile.fields.FieldPool;
import org.jmasonry.jvm.classfile.fields.FieldPoolFactory;
import org.jmasonry.jvm.types.TypeDeclaration;
import org.jmasonry.jvm.types.TypeDefinition;

public final class ClassFileFactory {
    private final ClassFileVersion version;

    public ClassFileFactory(ClassFileVersion version) {
        this.version = version;
    }

    public ClassFile create(TypeDefinition typeDefinition) {
        ConstantPoolBuilder constants = new ConstantPoolBuilder();
        ClassFileHeader header = createHeader(constants, typeDefinition.getDeclaration());
        FieldPoolFactory fieldPoolFactory = new FieldPoolFactory(constants);
        FieldPool fields = fieldPoolFactory.create(typeDefinition.getFields());

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
}
