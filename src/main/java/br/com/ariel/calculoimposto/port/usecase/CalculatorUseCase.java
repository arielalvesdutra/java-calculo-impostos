package br.com.ariel.calculoimposto.port.usecase;

import br.com.ariel.calculoimposto.domain.model.OperationEvent;

public interface CalculatorUseCase {


    OperationEvent process(
            OperationEvent neww,
            OperationEvent previous);

}
