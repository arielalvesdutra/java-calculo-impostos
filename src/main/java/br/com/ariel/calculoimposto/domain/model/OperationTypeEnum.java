package br.com.ariel.calculoimposto.domain.model;

import br.com.ariel.calculoimposto.domain.exception.BusinessRuleException;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.*;

import java.util.Arrays;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum OperationTypeEnum {

    BUY("buy"),
    SELL("sell");

    private String name;

    @JsonCreator
    public static OperationTypeEnum forName(String name) {
        return Arrays.stream(OperationTypeEnum.values())
                .filter(op -> op.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new BusinessRuleException("Entrada inválida de operação"));
    }

}
