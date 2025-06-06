package br.com.ariel.calculoimposto.domain.usecase.calculator;

import br.com.ariel.calculoimposto.domain.model.Order;
import br.com.ariel.calculoimposto.domain.model.OperationEvent;
import br.com.ariel.calculoimposto.port.usecase.CalculatorUseCase;

import java.math.BigDecimal;
import java.util.List;

public abstract class CalculatorUseCaseAbstract implements CalculatorUseCase {

    public OperationEvent process(
            OperationEvent neww,
            OperationEvent previous) {

        final var newTotalQuantityOfStocksPurchased = calculateTotalQuantityOfStocksPurchased(neww, previous);
        final var newBalance = calculateNewBalance(neww, previous);
        final var newTax = calculateNewTax(neww, previous);
        final var orders = updateOrders(neww, previous);

        return OperationEvent.builder()
                .operationType(neww.getOperationType())
                .quantity(neww.getQuantity())
                .value(neww.getValue())
                .totalQuantityOfStocksPurchased(newTotalQuantityOfStocksPurchased)
                .currentBalance(newBalance)
                .previousBalance(previous.getCurrentBalance())
                .currentTax(newTax)
                .orders(orders)
                .build();
    }

    protected abstract List<Order> updateOrders(
            OperationEvent neww,
            OperationEvent previous);

    protected abstract BigDecimal calculateNewBalance(
            OperationEvent neww,
            OperationEvent previous);

    protected abstract BigDecimal calculateNewTax(
            OperationEvent neww,
            OperationEvent previous);

    protected abstract Long calculateTotalQuantityOfStocksPurchased(
            OperationEvent neww,
            OperationEvent previous);

}
