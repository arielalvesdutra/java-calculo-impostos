package br.com.ariel.calculoimposto.domain.usecase.outputprinter;

import br.com.ariel.calculoimposto.domain.model.CalculationOutput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.List;


@RequiredArgsConstructor
public class PrintOutputUseCase {

    private final ObjectMapper mapper = new ObjectMapper();

    public void print(List<CalculationOutput> calculationsResult) {
        try {
            System.out.println("\n" + mapper.writeValueAsString(calculationsResult));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(String.format("Erro ao imprimir resultado: %s", e.getMessage()));
        }
    }
}
