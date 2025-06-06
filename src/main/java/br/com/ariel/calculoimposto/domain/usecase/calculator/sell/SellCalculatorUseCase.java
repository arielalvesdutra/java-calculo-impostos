package br.com.ariel.calculoimposto.domain.usecase.calculator.sell;

import br.com.ariel.calculoimposto.domain.model.Order;
import br.com.ariel.calculoimposto.domain.model.OperationEvent;
import br.com.ariel.calculoimposto.domain.usecase.calculator.CalculatorUseCaseAbstract;
import br.com.ariel.calculoimposto.domain.util.BigDecimalUtil;

import java.math.BigDecimal;
import java.util.List;

import static br.com.ariel.calculoimposto.domain.util.BigDecimalUtil.*;
import static java.util.Objects.isNull;
import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;


public class SellCalculatorUseCase extends CalculatorUseCaseAbstract {

    private static final BigDecimal TAX_PERCENTAGE_VALUE = java.math.BigDecimal.valueOf(20);
    private static final BigDecimal MAX_VALUE_WITHOUT_TAX_CHARGE = BigDecimal.valueOf(20000.00);


    @Override
    protected BigDecimal calculateNewBalance(
            OperationEvent neww,
            OperationEvent previous) {

        final var weightedAverageValue = previous.calculateWeightedAverageValue();
        final var newOperationTotalValue = BigDecimal.valueOf(neww.getQuantity()).multiply(neww.getValue());
        final var previousOrderValue = BigDecimal.valueOf(neww.getQuantity()).multiply(weightedAverageValue);
        final var previousBalance = isNull(previous.getCurrentBalance())
                ? BigDecimal.ZERO
                : previous.getCurrentBalance();

        if (operationIsProfit(neww, previous)
                && isLessOrEqualToTaxExemption(neww.calculateTotalOperation())) {
            return previousBalance;
        }

        var newBalance = BigDecimalUtil.isNegative(previousBalance)
                ? previousBalance.add(newOperationTotalValue.subtract(previousOrderValue))
                : newOperationTotalValue.subtract(previousOrderValue);
        neww.setCurrentBalance(newBalance);
        return newBalance;
    }

    @Override
    protected BigDecimal calculateNewTax(
            OperationEvent neww,
            OperationEvent previous) {

        if (isLessOrEqualToTaxExemption(neww.calculateTotalOperation())) {
            return BigDecimal.ZERO;
        }

        if (isOrderALoss(neww, previous)) {
            return BigDecimal.ZERO;
        }

        if (isBalanceNegativeOrZero(neww)) {
            return BigDecimal.ZERO;
        }

        return getValueInPercentage(neww.getCurrentBalance(), TAX_PERCENTAGE_VALUE);
    }

    @Override
    protected Long calculateTotalQuantityOfStocksPurchased(
            OperationEvent neww,
            OperationEvent previous) {

        final var totalCurrentQuantity = isNotEmpty(previous.getTotalQuantityOfStocksPurchased())
                ? previous.getTotalQuantityOfStocksPurchased()
                : 0L;
        return totalCurrentQuantity - neww.getQuantity();
    }

    @Override
    protected List<Order> updateOrders(OperationEvent neww, OperationEvent previous) {
        if (isNotEmpty(previous.getOrders()) && previous.getOrders().size() == 1) {
            Order currentOrder = previous.getOrders().get(0);
            Order updatedOrder = Order.builder()
                    .quantity(currentOrder.getQuantity() - neww.getQuantity())
                    .value(currentOrder.getValue())
                    .build();
            return List.of(updatedOrder);
        }

        return previous.getOrders();
    }

    private boolean isOrderALoss(OperationEvent novo, OperationEvent anterior) {
        return isLessOrEqualTo(novo.getValue(), anterior.calculateWeightedAverageValue());
    }

    private boolean isLessOrEqualToTaxExemption(BigDecimal totalOperationValue) {
        return isLessOrEqualTo(totalOperationValue, MAX_VALUE_WITHOUT_TAX_CHARGE);
    }

    private boolean isBalanceNegativeOrZero(OperationEvent novo) {
        return isLessOrEqualToZero(novo.getCurrentBalance());
    }

    private boolean operationIsProfit(OperationEvent neww, OperationEvent previous) {
        return BigDecimalUtil.isLessTo(previous.calculateWeightedAverageValue(), neww.getValue());
    }

}
