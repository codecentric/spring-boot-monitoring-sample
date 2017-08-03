package de.codecentric.springbootshowcase.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EchoMessage {

    private final String message;

    @JsonIgnore
    private String waste;

    public EchoMessage(final String message) {
        this.message = message;
        this.waste = "> " + this.message;
    }

    public String getMessage() {
        return this.message;
    }
}
