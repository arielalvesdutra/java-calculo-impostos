package br.com.ariel.calculoimposto.domain.usecase.calculator.validator;

import br.com.ariel.calculoimposto.domain.exception.BusinessRuleException;
import br.com.ariel.calculoimposto.domain.model.CalculationInput;

import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;

public class TaxCalculatorOrchestrationValidator {

    public void validateCalculationInput(CalculationInput calculationInput) {

        if (isEmpty(calculationInput)) {
            throw new BusinessRuleException("Entrada para calculo fornecida é invalida");
        }

        final List<String> errors = new ArrayList<>();

        if (isEmpty(calculationInput.getOperationType())) {
            errors.add("Tipo de Operação (buy ou sell) é obrigatório");
        }

        if (isEmpty(calculationInput.getQuantity())) {
            errors.add("Quantidade é obrigatório");
        }

        if (isEmpty(calculationInput.getUnitCost())) {
            errors.add("Custo da unidade é obrigatório é obrigatório");
        }

        if (isNotEmpty(errors)) {
            throw new BusinessRuleException(errors);
        }

    }

    public void validateInputList(List<CalculationInput> input) {

        if (isEmpty(input)) {
            throw new BusinessRuleException("Foi fornecida uma lista vazia para calculo. Favor preencher corretamente.");
        }

    }
}
