package br.com.ariel.calculoimposto.domain.usecase.calculator;

import br.com.ariel.calculoimposto.domain.model.CalculationInput;
import br.com.ariel.calculoimposto.domain.model.CalculationOutput;
import br.com.ariel.calculoimposto.domain.model.OperationEvent;
import br.com.ariel.calculoimposto.domain.model.OperationTypeEnum;
import br.com.ariel.calculoimposto.domain.usecase.calculator.buy.BuyCalculatorUseCase;
import br.com.ariel.calculoimposto.domain.usecase.calculator.sell.SellCalculatorUseCase;
import br.com.ariel.calculoimposto.domain.usecase.calculator.validator.TaxCalculatorOrchestrationValidator;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
public class TaxCalculatorOrchestrationUseCase {

    private final TaxCalculatorOrchestrationValidator validator;
    private final BuyCalculatorUseCase buyCalculatorUseCase;
    private final SellCalculatorUseCase sellCalculatorUseCase;

    public TaxCalculatorOrchestrationUseCase() {
        validator = new TaxCalculatorOrchestrationValidator();
        buyCalculatorUseCase = new BuyCalculatorUseCase();
        sellCalculatorUseCase = new SellCalculatorUseCase();
    }

    public List<CalculationOutput> process(List<CalculationInput> input) {

        validator.validateInputList(input);

        final var operationEvents = mapToOperationEvents(input);

        return processOperationsEvents(operationEvents);
    }

    private List<OperationEvent> mapToOperationEvents(List<CalculationInput> inputList) {
        return inputList.stream()
                .map(input -> {
                    validator.validateCalculationInput(input);

                    return OperationEvent.builder()
                            .operationType(input.getOperationType())
                            .quantity(input.getQuantity())
                            .value(input.getUnitCost())
                            .build();

                })
                .toList();
    }

    private List<CalculationOutput> processOperationsEvents(List<OperationEvent> operationEvents) {

        final List<CalculationOutput> output = new ArrayList<>();

        operationEvents.stream()
                .reduce(new OperationEvent(), (oldOperationEvent, newOperationEvent) -> {

                    final var newProcessedOperationEvent = OperationTypeEnum.BUY.equals(newOperationEvent.getOperationType())
                            ? buyCalculatorUseCase.process(newOperationEvent, oldOperationEvent)
                            : sellCalculatorUseCase.process(newOperationEvent, oldOperationEvent);

                    output.add(CalculationOutput.builder()
                            .tax(newProcessedOperationEvent.getCurrentTax())
                            .build());

                    return newProcessedOperationEvent;
                });

        return output;
    }

}
