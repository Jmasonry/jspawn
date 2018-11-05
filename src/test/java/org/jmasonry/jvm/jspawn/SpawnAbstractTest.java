package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.compiler.DefaultCompiler;
import org.jmasonry.jvm.types.MethodDefinition;
import org.jmasonry.jvm.types.MethodParameters;
import org.jmasonry.jvm.types.Type;
import org.jmasonry.jvm.types.Variable;

import java.util.Arrays;
import java.util.Collections;

import static org.jmasonry.jvm.types.MethodDeclaration.constructor;
import static org.jmasonry.vm.stack.instructions.StackInstructions.*;

abstract class SpawnAbstractTest {
    static final Type SELF_TYPE = Type.of("org.jmasonry.jvm.jspawn.Bar");

    private final Compiler compiler = new DefaultCompiler();
    final SpawnNest nest = new SpawnNest(compiler);

    MethodDefinition defaultConstructor(Type superClass) {
        return new MethodDefinition(constructor(),
                new MethodParameters(Collections.singletonList(new Variable("this", SELF_TYPE))),
                Arrays.asList(
                        loadLocal("this", SELF_TYPE),
                        call(superClass, constructor()),
                        returnTyped(Type.unit())
                ));
    }
}
