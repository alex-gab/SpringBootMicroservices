package com.example.exceptions;

public final class PersonNotFoundException extends RuntimeException {
    private Long personId;

    public Long getPersonId() {
        return personId;
    }

    public PersonNotFoundException(Long personId) {
        super("person# " + personId + " was not found");
        this.personId = personId;
    }
}
