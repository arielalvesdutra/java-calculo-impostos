package br.com.ariel.calculoimposto.domain.usecase.calculator;

import br.com.ariel.calculoimposto.domain.model.CalculationInput;
import br.com.ariel.calculoimposto.domain.model.OperationTypeEnum;
import br.com.ariel.calculoimposto.domain.usecase.calculator.buy.BuyCalculatorUseCase;
import br.com.ariel.calculoimposto.domain.usecase.calculator.sell.SellCalculatorUseCase;
import br.com.ariel.calculoimposto.domain.usecase.calculator.validator.TaxCalculatorOrchestrationValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class TaxCalculatorOrchestrationUseCaseTest {

    @InjectMocks
    private TaxCalculatorOrchestrationUseCase useCase;

    @Spy
    private TaxCalculatorOrchestrationValidator validator;
    @Spy
    private BuyCalculatorUseCase buyCalculatorUseCase;
    @Spy
    private SellCalculatorUseCase sellCalculatorUseCase;

    @Test
    void process_case1_shouldWork() {
         var result = useCase.process(mockCase1());

         assertThat(result).isNotEmpty().size().isEqualTo(3);

         assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
         assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
         assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.ZERO);

         verify(validator, times(1)).validateInputList(any());
         verify(buyCalculatorUseCase, times(1)).process(any(), any());
         verify(sellCalculatorUseCase, times(2)).process(any(), any());
    }

    @Test
    void process_case2_shouldWork() {
        var result = useCase.process(mockCase2());

        assertThat(result).isNotEmpty().size().isEqualTo(3);

        assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.valueOf(10000));
        assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.ZERO);

        verify(validator, times(1)).validateInputList(any());
        verify(buyCalculatorUseCase, times(1)).process(any(), any());
        verify(sellCalculatorUseCase, times(2)).process(any(), any());
    }

    @Test
    void process_case3_shouldWork() {
        var result = useCase.process(mockCase3());

        assertThat(result).isNotEmpty().size().isEqualTo(3);

        assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.valueOf(1000));

        verify(validator, times(1)).validateInputList(any());
        verify(buyCalculatorUseCase, times(1)).process(any(), any());
        verify(sellCalculatorUseCase, times(2)).process(any(), any());
    }

    @Test
    void process_case4_shouldWork() {
        var result = useCase.process(mockCase4());

        assertThat(result).isNotEmpty().size().isEqualTo(3);

        assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.ZERO);

        verify(validator, times(1)).validateInputList(any());
        verify(buyCalculatorUseCase, times(2)).process(any(), any());
        verify(sellCalculatorUseCase, times(1)).process(any(), any());
    }

    @Test
    void process_case5_shouldWork() {
        var result = useCase.process(mockCase5());

        assertThat(result).isNotEmpty().size().isEqualTo(4);

        assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(3).getTax()).isEqualByComparingTo(BigDecimal.valueOf(10000));

        verify(validator, times(1)).validateInputList(any());
        verify(buyCalculatorUseCase, times(2)).process(any(), any());
        verify(sellCalculatorUseCase, times(2)).process(any(), any());
    }

    @Test
    void process_case6_shouldWork() {
        var result = useCase.process(mockCase6());

        assertThat(result).isNotEmpty().size().isEqualTo(5);

        assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(3).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(4).getTax()).isEqualByComparingTo(BigDecimal.valueOf(3000));

        verify(validator, times(1)).validateInputList(any());
        verify(buyCalculatorUseCase, times(1)).process(any(), any());
        verify(sellCalculatorUseCase, times(4)).process(any(), any());
    }

    @Test
    void process_case7_shouldWork() {
        var result = useCase.process(mockCase7());

        assertThat(result).isNotEmpty().size().isEqualTo(9);

        assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(3).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(4).getTax()).isEqualByComparingTo(BigDecimal.valueOf(3000));
        assertThat(result.get(5).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(6).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(7).getTax()).isEqualByComparingTo(BigDecimal.valueOf(3700));
        assertThat(result.get(8).getTax()).isEqualByComparingTo(BigDecimal.ZERO);

        verify(validator, times(1)).validateInputList(any());
        verify(buyCalculatorUseCase, times(2)).process(any(), any());
        verify(sellCalculatorUseCase, times(7)).process(any(), any());
    }

    @Test
    void process_case8_shouldWork() {
        var result = useCase.process(mockCase8());

        assertThat(result).isNotEmpty().size().isEqualTo(4);

        assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.valueOf(80000));
        assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(3).getTax()).isEqualByComparingTo(BigDecimal.valueOf(60000));

        verify(validator, times(1)).validateInputList(any());
        verify(buyCalculatorUseCase, times(2)).process(any(), any());
        verify(sellCalculatorUseCase, times(2)).process(any(), any());
    }

    @Test
    void process_case9_shouldWork() {
        var result = useCase.process(mockCase9());

        assertThat(result).isNotEmpty().size().isEqualTo(8);

        assertThat(result.get(0).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(1).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(2).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(3).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(4).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(5).getTax()).isEqualByComparingTo(BigDecimal.ZERO);
        assertThat(result.get(6).getTax()).isEqualByComparingTo(BigDecimal.valueOf(1000));
        assertThat(result.get(7).getTax()).isEqualByComparingTo(BigDecimal.valueOf(2400));

        verify(validator, times(1)).validateInputList(any());
        verify(buyCalculatorUseCase, times(4)).process(any(), any());
        verify(sellCalculatorUseCase, times(4)).process(any(), any());
    }

    private List<CalculationInput> mockCase1() {
        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(10))
                        .quantity(100L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(15))
                        .quantity(50L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(15))
                        .quantity(50L)
                        .operationType(OperationTypeEnum.SELL)
                        .build()
        );
    }

    private List<CalculationInput> mockCase2() {
        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(10))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(5))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build()
        );
    }

    private List<CalculationInput> mockCase3() {

        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(10))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(5))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20))
                        .quantity(3000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build()
        );
    }

    private List<CalculationInput> mockCase4() {

        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(10))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(25))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(15))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build()
        );
    }

    private List<CalculationInput> mockCase5() {
        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(10))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(25))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(15))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(25))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build());
    }

    private List<CalculationInput> mockCase6() {
        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(10))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(2))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20))
                        .quantity(2000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20))
                        .quantity(2000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(25))
                        .quantity(1000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build());
    }

    private List<CalculationInput> mockCase7() {
        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(10))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(2))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20))
                        .quantity(2000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20))
                        .quantity(2000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(25))
                        .quantity(1000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(15))
                        .quantity(5000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(30))
                        .quantity(4350L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(30))
                        .quantity(650L)
                        .operationType(OperationTypeEnum.SELL)
                        .build());
    }

    private List<CalculationInput> mockCase8() {
        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(10))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(50))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(50))
                        .quantity(10000L)
                        .operationType(OperationTypeEnum.SELL)
                        .build());
    }

    private List<CalculationInput> mockCase9() {
        return List.of(
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(5000))
                        .quantity(10L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(4000))
                        .quantity(5L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(15000))
                        .quantity(5L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(4000))
                        .quantity(2L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(23000))
                        .quantity(2L)
                        .operationType(OperationTypeEnum.BUY)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(20000))
                        .quantity(1L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(12000))
                        .quantity(10L)
                        .operationType(OperationTypeEnum.SELL)
                        .build(),
                CalculationInput.builder()
                        .unitCost(BigDecimal.valueOf(15000))
                        .quantity(3L)
                        .operationType(OperationTypeEnum.SELL)
                        .build());
    }

}
