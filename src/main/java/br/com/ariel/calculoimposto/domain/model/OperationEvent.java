package br.com.ariel.calculoimposto.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isEmpty;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationEvent {


    private OperationTypeEnum operationType;
    private BigDecimal value;
    private Long quantity;

    private BigDecimal currentTax;
    private BigDecimal currentBalance;
    private BigDecimal previousBalance;

    private Long totalQuantityOfStocksPurchased;

    private List<Order> orders;


    public BigDecimal calculateTotalOperation() {
        return BigDecimal.valueOf(quantity).multiply(value);
    }

    public BigDecimal calculateWeightedAverageValue() {
        if (isEmpty(this.getOrders())) {
            return BigDecimal.ZERO;
        }

        var dividend = this.getOrders()
                .stream()
                .map(order -> order.getValue().multiply(BigDecimal.valueOf(order.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var divider = this.getOrders()
                .stream()
                .map(order -> BigDecimal.valueOf(order.getQuantity()))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return dividend.divide(divider, 2, RoundingMode.HALF_UP);
    }

}
