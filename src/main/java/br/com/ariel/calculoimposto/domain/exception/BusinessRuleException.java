package br.com.ariel.calculoimposto.domain.exception;


import java.util.List;

public class BusinessRuleException extends RuntimeException {

    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(List<String> errors) {
        super(String.join(", ", errors));
    }

}
