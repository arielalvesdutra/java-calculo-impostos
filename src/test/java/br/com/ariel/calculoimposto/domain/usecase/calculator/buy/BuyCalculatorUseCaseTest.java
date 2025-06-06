package br.com.ariel.calculoimposto.domain.usecase.calculator.buy;

import br.com.ariel.calculoimposto.domain.model.OperationEvent;
import br.com.ariel.calculoimposto.domain.model.OperationTypeEnum;
import br.com.ariel.calculoimposto.domain.model.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class BuyCalculatorUseCaseTest {

    @InjectMocks
    private BuyCalculatorUseCase useCase;

    @Test
    @DisplayName("""
        Processar operação com a primeira compra de estoques
         deve funcionar
    """)
    void process_withFirthBuyEvent_shouldWork() {
        OperationEvent neww = mockFirtBuyOperationEvent();
        OperationEvent previous = OperationEvent.builder().build();


        var result = useCase.process(neww, previous);

        assertThat(result).isNotNull();
        assertThat(result.getCurrentTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.getCurrentBalance()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.getValue()).isEqualByComparingTo(neww.getValue());
        assertThat(result.getQuantity()).isEqualByComparingTo(neww.getQuantity());
        assertThat(result.getTotalQuantityOfStocksPurchased()).isEqualTo(10000L);
        assertThat(result.getOrders()).isNotEmpty().size().isEqualTo(1);
    }

    @Test
    @DisplayName("Calculo de taxa para compra deve sempre retornar zero")
    void calculateNewTax_shouldRetornZero() {
        OperationEvent neww = mockFirtBuyOperationEvent();
        OperationEvent previous = OperationEvent.builder().build();

        var result = useCase.calculateNewTax(neww, previous);

        assertThat(result).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Calcular saldo da primeira compra deve funcionar")
    void calculateNewBalance_withFirstBuy_shouldWork() {
        OperationEvent neww = mockFirtBuyOperationEvent();
        OperationEvent previous = OperationEvent.builder().build();

        var result = useCase.calculateNewBalance(neww, previous);

        assertThat(result).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Calcular total de estoques disponiveis deve funcionar")
    void calculateTotalQuantityOfStocksPurchased_shouldWork() {
        OperationEvent neww = mockFirtBuyOperationEvent();
        OperationEvent previous = OperationEvent.builder().build();

        var result = useCase.calculateTotalQuantityOfStocksPurchased(neww, previous);

        assertThat(result).isEqualTo(10000L);
    }

    @Test
    @DisplayName("Atualizar compras deve funcionar")
    void updateOrders_shouldWork() {
        OperationEvent neww = mockFirtBuyOperationEvent();
        OperationEvent previous = OperationEvent.builder().build();

        var result = useCase.updateOrders(neww, previous);

        assertThat(result).isNotEmpty().size().isEqualTo(1);
    }

    private OperationEvent mockFirtBuyOperationEvent() {
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
                .build();
    }

}
