package com.loja.loja.service.exceptions;

import com.loja.loja.controle.exceptions.StandardError;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
    private static final long serialVersionUID = 1L;

    public ValidationError(Long timestmp, Integer status, String error) {
        super(timestmp, status, error);
    }


    private List<FieldMessage> errors = new ArrayList<>();

    public List<FieldMessage> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        this.errors.add(new FieldMessage(fieldName, message));
    }


}