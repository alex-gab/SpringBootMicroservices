package com.example.controllers;

import com.example.exceptions.PersonNotFoundException;
import org.springframework.hateoas.VndErrors;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.Optional;

@ControllerAdvice
@RequestMapping(produces = "application/vnd.error")
@ResponseBody
public final class PersonControllerAdvice {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(FileNotFoundException.class)
    public final VndErrors fileNotFoundException(final FileNotFoundException ex) {
        return this.error(ex, ex.getLocalizedMessage());
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(PersonNotFoundException.class)
    public final VndErrors personNotFoundException(final PersonNotFoundException e) {
        return this.error(e, e.getPersonId() + "");
    }

    private <E extends Exception> VndErrors error(final E e, final String logref) {
        final String msg = Optional.of(e.getMessage()).orElse(e.getClass().getSimpleName());
        return new VndErrors(logref, msg);
    }
}
