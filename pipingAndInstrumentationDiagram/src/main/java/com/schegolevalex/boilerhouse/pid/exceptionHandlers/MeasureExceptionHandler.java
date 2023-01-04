package com.schegolevalex.boilerhouse.pid.exceptionHandlers;

import com.schegolevalex.boilerhouse.unit_library.models.measures.exceptions.IllegalMeasureException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class MeasureExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IllegalMeasureException.class)
    protected ResponseEntity<ExceptionMessage> handleIllegalMeasureException(IllegalMeasureException ex) {
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setInfo(ex.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        if (Objects
                .requireNonNull(ex.getRequiredType())
                .getName()
                .equals("com.schegolevalex.boilerhouse.unit_library.models.units.Unit"))
            exceptionMessage.setInfo(ExceptionUtils.getRootCause(ex).getMessage());
        else exceptionMessage.setInfo(ex.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }
}
