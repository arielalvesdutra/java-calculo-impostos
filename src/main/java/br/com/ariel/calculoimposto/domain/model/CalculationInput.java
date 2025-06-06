package br.com.ariel.calculoimposto.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CalculationInput {

    @JsonProperty("operation")
    private OperationTypeEnum operationType;
    @JsonProperty("unit-cost")
    private BigDecimal unitCost;
    private Long quantity;

}
