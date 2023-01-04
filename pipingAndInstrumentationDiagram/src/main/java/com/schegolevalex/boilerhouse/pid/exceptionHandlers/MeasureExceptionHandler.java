package com.schegolevalex.boilerhouse.pid.exceptionHandlers;

import com.schegolevalex.boilerhouse.unit_library.models.measures.exceptions.IllegalMeasureException;
import com.schegolevalex.boilerhouse.unit_library.models.measures.exceptions.IllegalUnitException;
import com.schegolevalex.boilerhouse.unit_library.models.measures.exceptions.IllegalValueException;
import com.schegolevalex.boilerhouse.unit_library.models.units.Unit;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

@ControllerAdvice
public class MeasureExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IllegalUnitException.class)
    protected ResponseEntity<ExceptionMessage> handleIllegalUnitException(IllegalUnitException ex) {
        return new ResponseEntity<>(getExceptionMessage(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = {IllegalValueException.class, IllegalMeasureException.class})
    protected ResponseEntity<ExceptionMessage> handleIllegalValueException(IllegalMeasureException ex) {
        return new ResponseEntity<>(getExceptionMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ExceptionMessage> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (Objects.requireNonNull(ex.getRequiredType()).equals(Unit.class))
            return new ResponseEntity<>(getExceptionMessage(ExceptionUtils.getRootCause(ex).getMessage()), HttpStatus.BAD_REQUEST);
        else
            return new ResponseEntity<>(getExceptionMessage(ex.getMessage()), HttpStatus.BAD_REQUEST);

    }

    @NotNull
    private ExceptionMessage getExceptionMessage(String message) {
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setInfo(message);
        return exceptionMessage;
    }
}
