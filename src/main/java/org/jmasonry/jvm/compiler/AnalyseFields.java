package org.jmasonry.jvm.compiler;

import org.jmasonry.jvm.classfile.fields.FieldPoolBuilder;
import org.jmasonry.jvm.types.FieldDeclaration;

import java.util.List;

class AnalyseFields implements CompilationStep {
    private final FieldPoolBuilder builder;
    private List<FieldDeclaration> fields;

    AnalyseFields(FieldPoolBuilder builder, List<FieldDeclaration> fields) {
        this.builder = builder;
        this.fields = fields;
    }

    @Override
    public void execute(CompilationUnitBuilder ignored) {
        for (FieldDeclaration field : fields) {
            builder.add(field);
        }
    }
}
