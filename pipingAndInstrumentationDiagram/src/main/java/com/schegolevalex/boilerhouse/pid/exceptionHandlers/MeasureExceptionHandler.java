package com.schegolevalex.boilerhouse.pid.exceptionHandlers;

import com.schegolevalex.boilerhouse.unit_library.models.measures.exceptions.IllegalMeasureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class MeasureExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IllegalMeasureException.class, MethodArgumentTypeMismatchException.class})
    protected ResponseEntity<MeasureIncorrectData> handleException(Exception ex) {
        MeasureIncorrectData measureIncorrectData = new MeasureIncorrectData();
        measureIncorrectData.setInfo(ex.getMessage());
        return new ResponseEntity<>(measureIncorrectData, HttpStatus.BAD_REQUEST);
    }
}
