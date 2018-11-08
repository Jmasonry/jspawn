package org.jmasonry.jvm.jspawn;

import org.jmasonry.jvm.compiler.Compiler;
import org.jmasonry.jvm.compiler.DefaultCompiler;
import org.jmasonry.jvm.types.*;

import java.util.Arrays;
import java.util.Collections;

import static org.jmasonry.jvm.types.MethodDeclaration.constructor;
import static org.jmasonry.vm.stack.instructions.StackInstructions.*;

abstract class SpawnAbstractTest {
    static final Type SELF_TYPE = Type.of("org.jmasonry.jvm.jspawn.Bar");
    static final Type PARENT_TYPE = Type.of(Foo.class);
    static final TypeDeclaration TYPE_DECLARATION = TypeDeclaration.create(SELF_TYPE, PARENT_TYPE);

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

    // all must be public
    public static class Foo {
        public Foo() {
        }
    }
}
