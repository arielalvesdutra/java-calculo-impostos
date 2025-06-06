package br.com.ariel.calculoimposto.domain.usecase.inputparser;

import br.com.ariel.calculoimposto.domain.model.CalculationInput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;


@RequiredArgsConstructor
public class InputParserUseCase {

    private final ObjectMapper mapper = new ObjectMapper();

    public List<CalculationInput> parseCommandLineInputString(String args) {

        try {

            var stringWithoutWhitespace = args.replaceAll("\\s", "").replaceAll(" ", "");
            var result = mapper.readValue(stringWithoutWhitespace, CalculationInput[].class);
            return Arrays.stream(result).toList();

        } catch (JsonProcessingException e) {
            var message = "Ocorreu um erro ao tentar ler entrada de texto. Mensagem: " + e.getMessage();
            System.out.println(message);
            throw new RuntimeException(message);
        }
    }

}
