package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.types.*;
import org.jmasonry.vm.stack.instructions.StackInstruction;

import java.util.Arrays;
import java.util.List;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.jmasonry.jvm.types.Type.unit;
import static org.jmasonry.vm.stack.instructions.StackInstructions.*;

final class Declarations {
    static final Type SELF_TYPE = Type.of("org.jmasonry.jvm.jspawn.Bar");
    static final Type PARENT_TYPE = Type.of(Foo.class);
    static final TypeDeclaration TYPE_DECLARATION = TypeDeclaration.create(SELF_TYPE, PARENT_TYPE);

    static final Variable THIS = new Variable("this", SELF_TYPE);
    static final MethodParameters THIS_PARAMETER = new MethodParameters(singletonList(THIS));


    static TypeDefinition typeWithSingleMethod(String name, Type returnedType, List<StackInstruction> instructions) {
        return TypeDefinition.of(TYPE_DECLARATION, emptyList(), Arrays.asList(
                constructor(),
                methodDef(name, returnedType, instructions)
        ));
    }

    static MethodDefinition constructor() {
        return methodDef("<init>", unit(), Arrays.asList(
                loadLocal("this", SELF_TYPE),
                call(PARENT_TYPE, new MethodDeclaration("<init>", unit())),
                returnTyped(unit())));
    }

    static MethodDefinition methodDef(String name, Type returnedType, List<StackInstruction> instructions) {
        MethodDeclaration methodDeclaration = new MethodDeclaration(name, returnedType);
        return new MethodDefinition(methodDeclaration, THIS_PARAMETER, instructions);
    }

    // all must be public
    public static class Foo {
        public Foo() {
        }
    }
}
