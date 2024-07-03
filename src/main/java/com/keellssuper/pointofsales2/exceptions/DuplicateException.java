package com.keellssuper.pointofsales2.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (value = HttpStatus.NOT_FOUND)
public class DuplicateException extends RuntimeException{
    public DuplicateException(String message){
        super(message);
    }
}
