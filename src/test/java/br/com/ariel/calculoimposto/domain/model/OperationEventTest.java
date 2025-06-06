package br.com.ariel.calculoimposto.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class OperationEventTest {

    @Test
    void calculateWeightedAverageValue_withOneOrderShouldWork() {
        List<Order> orders = mockOneOrder();
        OperationEvent event = OperationEvent.builder()
                .orders(orders)
                .build();

        assertThat(event.calculateWeightedAverageValue()).isEqualByComparingTo(BigDecimal.valueOf(20));
    }

    private List<Order> mockOneOrder() {
        return List.of(Order.builder()
                .value(BigDecimal.valueOf(20))
                .quantity(10000L)
                .build());
    }

}
