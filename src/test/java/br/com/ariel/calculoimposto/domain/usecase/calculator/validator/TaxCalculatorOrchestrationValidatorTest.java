package br.com.ariel.calculoimposto.domain.usecase.calculator.validator;

import br.com.ariel.calculoimposto.domain.exception.BusinessRuleException;
import br.com.ariel.calculoimposto.domain.model.CalculationInput;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class TaxCalculatorOrchestrationValidatorTest {

    @InjectMocks
    private TaxCalculatorOrchestrationValidator validator;

    @Test
    @DisplayName("Entrada com valor nulo deve lancar exception")
    void validateInputList_withNullInput_shouldThrowException() {
        var resultado = assertThrows(BusinessRuleException.class,
                () -> validator.validateInputList(null));

        assertThat(resultado).isNotNull();
        assertThat(resultado.getMessage()).isEqualTo("Foi fornecida uma lista vazia para calculo. Favor preencher corretamente.");
    }

    @Test
    @DisplayName("Entrada invalida de calculo deve lançar exception")
    void validateCalculationInput_comObjetoInvalido_deveLancarException() {
        var resultado = assertThrows(BusinessRuleException.class,
                () -> validator.validateCalculationInput(new CalculationInput()));

        assertThat(resultado).isNotNull();
        assertThat(resultado.getMessage()).contains("Tipo de Operação (buy ou sell) é obrigatório");
    }

}
