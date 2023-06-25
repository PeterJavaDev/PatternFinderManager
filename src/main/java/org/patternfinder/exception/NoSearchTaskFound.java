package org.patternfinder.exception;

public class NoSearchTaskFound extends RuntimeException {
    public NoSearchTaskFound() {
        super("No search task for provided ID found.");
    }
}
