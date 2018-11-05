package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.compiler.DefaultCompiler;
import org.jmasonry.jvm.types.*;

import java.util.Arrays;
import java.util.Collections;

import static org.jmasonry.vm.stack.instructions.StackInstructions.*;

abstract class SpawnAbstractTest {
    static final Type SELF_TYPE = Type.of("org.jmasonry.jvm.jspawn.Bar");

    private final Compiler compiler = new DefaultCompiler();
    final SpawnNest nest = new SpawnNest(compiler);

    MethodDefinition defaultConstructor(Type superClass) {
        MethodDeclaration constructorDeclaration = new MethodDeclaration("<init>");
        return new MethodDefinition(constructorDeclaration,
                new MethodParameters(Collections.singletonList(new Variable("this", SELF_TYPE))),
                Arrays.asList(
                        loadLocal("this", SELF_TYPE),
                        call(superClass, new MethodDeclaration("<init>")),
                        returnTyped(Type.unit())
                ));
    }
}
