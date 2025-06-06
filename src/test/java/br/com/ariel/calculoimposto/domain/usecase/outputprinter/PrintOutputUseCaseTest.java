package br.com.ariel.calculoimposto.domain.usecase.outputprinter;

import br.com.ariel.calculoimposto.domain.model.CalculationOutput;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class PrintOutputUseCaseTest {

    @InjectMocks
    private PrintOutputUseCase useCase;

    @Spy
    private ObjectMapper mapper;

    @Test
    @DisplayName("Com entrada valida deve imprimir resultado")
    void print_withValidInput_shouldWork() {
        CalculationOutput output = CalculationOutput.builder()
                .tax(BigDecimal.valueOf(300))
                .build();
        List<CalculationOutput> ouputList = List.of(output);

        assertDoesNotThrow(() -> useCase.print(ouputList));
    }

}
