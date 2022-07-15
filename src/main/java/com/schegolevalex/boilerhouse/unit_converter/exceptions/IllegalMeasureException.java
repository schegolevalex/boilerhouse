package com.schegolevalex.boilerhouse.unit_converter.exceptions;

public class IllegalMeasureException extends IllegalArgumentException {
    // задумка сделать общее родительское исключение для неправильного значения и неправильной единицы измерения todo

    public IllegalMeasureException(String s) {
        super(s);
    }
}
