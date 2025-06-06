package br.com.ariel.calculoimposto.domain.usecase.inputparser;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class InputParserUseCaseTest {

    @InjectMocks
    private InputParserUseCase useCase;

    @Test
    @DisplayName("Processar entrada de lista deve funcionar")
    void parseCommandLineInputString_withValidList_shouldWork() {
        var result = useCase.parseCommandLineInputString("[{\"operation\":\"buy\",\"unit-cost\":10.00,\"quantity\":10000}]");

        assertThat(result).isNotEmpty();
        assertThat(result).size().isEqualTo(1);
    }

    @Test
    @DisplayName("Processar com lista invalida deve lançar erro")
    void parseCommandLineInputString_withInvalidInput_shouldThrowError() {
        var result = assertThrows(RuntimeException.class,
                () -> useCase.parseCommandLineInputString("[{\"operation\":\"buy\",\"unit-cost\":10.00,\"quantity\":1000"));

        assertThat(result).isNotNull();
        assertThat(result.getMessage()).contains("Ocorreu um erro ao tentar ler entrada de texto.");
    }

}
