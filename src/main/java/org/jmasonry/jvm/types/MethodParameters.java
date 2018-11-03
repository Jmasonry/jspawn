package org.jmasonry.jvm.types;

import java.util.List;
import java.util.stream.Collectors;

public final class MethodParameters {
    private final List<Variable> variables;

    public MethodParameters(List<Variable> variables) {
        this.variables = variables;
    }

    public List<String> names(){
        return variables.stream().map(Variable::getName).collect(Collectors.toList());
    }
}
