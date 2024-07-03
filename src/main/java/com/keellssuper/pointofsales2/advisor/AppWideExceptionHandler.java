package com.keellssuper.pointofsales2.advisor;

import com.keellssuper.pointofsales2.exceptions.DuplicateException;
import com.keellssuper.pointofsales2.exceptions.NotFoundException;
import com.keellssuper.pointofsales2.util.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> notFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Error",e.getMessage()),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<StandardResponse> duplicateException(DuplicateException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(400,"Duplication Error",e.getMessage()),
                HttpStatus.BAD_REQUEST
        );
    }
}
