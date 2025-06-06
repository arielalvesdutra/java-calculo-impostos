package br.com.ariel.calculoimposto.domain.usecase.calculator.sell;

import br.com.ariel.calculoimposto.domain.model.OperationEvent;
import br.com.ariel.calculoimposto.domain.model.OperationTypeEnum;
import br.com.ariel.calculoimposto.domain.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class SellCalculatorUseCaseTest {

    @InjectMocks
    private SellCalculatorUseCase useCase;

    @Test
    @DisplayName("Processar evento de venda deve funcionar")
    void process_shouldWork() {
        OperationEvent neww = mockNewSellOperationEvent();
        OperationEvent previous = mockPreviousBuyOperationEvent();

        var result = useCase.process(neww, previous);

        assertThat(result).isNotNull();
        assertThat(result.getCurrentBalance()).isEqualByComparingTo(BigDecimal.valueOf(150000));
        assertThat(result.getCurrentTax()).isEqualByComparingTo(BigDecimal.valueOf(30000));
        assertThat(result.getTotalQuantityOfStocksPurchased()).isEqualTo(4000L);
    }

    @Test
    @DisplayName("Calcular saldo em caso de lucro deve funcionar")
    void calculateNewBalance_withProfitCase_shouldWork() {
        OperationEvent neww = mockNewSellOperationEvent();
        OperationEvent previous = mockPreviousBuyOperationEvent();

        var result = useCase.calculateNewBalance(neww, previous);

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(150000));
    }

    @Test
    @DisplayName("""
            Calcular imposto em case que se deve cobrar imposto 
             deve funcionar
            """)
    void calculateNewTax_withCaseToChargeTaxt_shouldWork() {
        OperationEvent neww = mockNewSellOperationEvent();
        neww.setCurrentBalance(BigDecimal.valueOf(150000));
        OperationEvent previous = mockPreviousBuyOperationEvent();

        var result = useCase.calculateNewTax(neww, previous);

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(30000));
    }

    @Test
    @DisplayName("Calcular a nova quantidade total de estoques deve funcionar")
    void calculateTotalQuantityOfStocksPurchased_shouldWork() {
        OperationEvent neww = mockNewSellOperationEvent();
        OperationEvent previous = mockPreviousBuyOperationEvent();

        var result = useCase.calculateTotalQuantityOfStocksPurchased(neww, previous);

        assertThat(result).isEqualTo(4000L);
    }

    @Test
    @DisplayName("Atualizar compras de uma venda deve funcionar")
    void updateOrders_shoulWork() {
        OperationEvent neww = mockNewSellOperationEvent();
        OperationEvent previous = mockPreviousBuyOperationEvent();

        var result = useCase.updateOrders(neww, previous);

        assertThat(result).isNotEmpty().size().isEqualTo(1);
    }

    private OperationEvent mockPreviousBuyOperationEvent() {
        var order = Order.builder()
                .quantity(10000L)
                .value(BigDecimal.valueOf(25))
                .build();
        return OperationEvent.builder()
                .operationType(OperationTypeEnum.BUY)
                .quantity(order.getQuantity())
                .value(order.getValue())
                .totalQuantityOfStocksPurchased(order.getQuantity())
                .currentBalance(BigDecimal.ZERO)
                .currentTax(BigDecimal.ZERO)
                .orders(List.of(order))
                .build();
    }

    private OperationEvent mockNewSellOperationEvent() {
        return OperationEvent.builder()
                .operationType(OperationTypeEnum.SELL)
                .quantity(6000L)
                .value(BigDecimal.valueOf(50))
                .currentBalance(BigDecimal.ZERO)
                .build();
    }

}
