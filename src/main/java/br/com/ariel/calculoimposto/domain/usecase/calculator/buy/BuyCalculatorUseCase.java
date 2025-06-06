package br.com.ariel.calculoimposto.domain.usecase.calculator.buy;

import br.com.ariel.calculoimposto.domain.model.Order;
import br.com.ariel.calculoimposto.domain.model.OperationEvent;
import br.com.ariel.calculoimposto.domain.usecase.calculator.CalculatorUseCaseAbstract;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.apache.commons.lang3.ObjectUtils.isNotEmpty;
import static org.apache.commons.lang3.ObjectUtils.notEqual;


public class BuyCalculatorUseCase extends CalculatorUseCaseAbstract {


    @Override
    protected BigDecimal calculateNewTax(OperationEvent neww, OperationEvent previous) {
        return BigDecimal.ZERO;
    }

    @Override
    protected BigDecimal calculateNewBalance(OperationEvent neww, OperationEvent previous) {
        if (isNotEmpty(previous.getTotalQuantityOfStocksPurchased())
                && previous.getTotalQuantityOfStocksPurchased().equals(0L)) {
            return BigDecimal.ZERO;
        }

        return isNotEmpty(previous.getCurrentBalance())
                ? previous.getCurrentBalance()
                : BigDecimal.ZERO;
    }

    @Override
    protected Long calculateTotalQuantityOfStocksPurchased(
            OperationEvent neww,
            OperationEvent previous) {

        final var totalCurrentQuantity = isNotEmpty(previous.getTotalQuantityOfStocksPurchased())
                ? previous.getTotalQuantityOfStocksPurchased()
                : 0L;

        return totalCurrentQuantity + neww.getQuantity();
    }

    // todo: talvez aqui possa ter uma regra de diminuir a quantidade quando tiver so uma compra
    @Override
    protected List<Order> updateOrders(OperationEvent neww, OperationEvent previous) {
        final List<Order> orders = new ArrayList<>();

        if (shouldConsiderPreviousOrders(previous)) {
            orders.addAll(previous.getOrders());
        }

        if (isNotEmpty(neww.getOrders())) {
            orders.addAll(neww.getOrders());
        }

        orders.add(Order.builder()
                .quantity(neww.getQuantity())
                .value(neww.getValue())
                .build());

        return orders;
    }

    private boolean shouldConsiderPreviousOrders(OperationEvent previous) {
        return isNotEmpty(previous.getOrders())
                && isNotEmpty(previous.getTotalQuantityOfStocksPurchased())
                && notEqual(previous.getTotalQuantityOfStocksPurchased(), (0L));
    }

}
