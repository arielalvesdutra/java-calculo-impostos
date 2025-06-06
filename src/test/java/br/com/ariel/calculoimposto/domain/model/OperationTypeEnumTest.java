package br.com.ariel.calculoimposto.domain.model;

import br.com.ariel.calculoimposto.domain.exception.BusinessRuleException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OperationTypeEnumTest {


    @Test
    @DisplayName("Instanciar enum de Tipo Operação de buy")
    void fromName_buy() {
        assertThat(OperationTypeEnum.forName("buy")).isEqualTo(OperationTypeEnum.BUY);
    }

    @DisplayName("Instanciar enum de Tipo Operação de sell")
    @Test
    void fromName_sell() {
        assertThat(OperationTypeEnum.forName("sell")).isEqualTo(OperationTypeEnum.SELL);
    }

    @Test
    @DisplayName("Instanciar enum de Tipo Operação com valor invalido")
    void fromName_invalid() {
     var result = assertThrows(BusinessRuleException.class, () -> OperationTypeEnum.forName("invalid"));

     assertThat(result).isNotNull();
     assertThat(result.getMessage()).isEqualTo("Entrada inválida de operação");
    }

}
