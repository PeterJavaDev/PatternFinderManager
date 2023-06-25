package org.patternfinder.model;

public enum SearchTaskStatus {

    READY(1),
    IN_PROGRESS(2),
    DONE(3);

    private int code;

    SearchTaskStatus(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public static SearchTaskStatus fromCode(int code) {
        for (SearchTaskStatus status : SearchTaskStatus.values()) {
            if (status.getCode() == code) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid status code: " + code);
    }

}
