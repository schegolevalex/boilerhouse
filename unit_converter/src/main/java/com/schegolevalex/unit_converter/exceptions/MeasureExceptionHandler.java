package com.schegolevalex.unit_converter.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MeasureExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<MeasureIncorrectData> handleException (IllegalMeasureException ex) {
        MeasureIncorrectData measureIncorrectData = new MeasureIncorrectData();
        measureIncorrectData.setInfo(ex.getMessage());
        return new ResponseEntity<>(measureIncorrectData, HttpStatus.BAD_REQUEST);
    }
}
