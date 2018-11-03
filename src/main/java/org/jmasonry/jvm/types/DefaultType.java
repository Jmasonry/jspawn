package org.jmasonry.jvm.types;

import java.util.Objects;

final class DefaultType implements Type {
    private final String name;

    DefaultType(String name) {this.name = name;}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof DefaultType)) {
            return false;
        }
        DefaultType that = (DefaultType) object;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
