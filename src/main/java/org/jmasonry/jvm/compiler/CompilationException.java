package org.jmasonry.jvm.compiler;

final class CompilationException extends RuntimeException {
    CompilationException(String message) {
        super(message);
    }

    CompilationException(String message, Throwable cause) {
        super(message, cause);
    }
}
