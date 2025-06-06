package br.com.ariel.calculoimposto;

import br.com.ariel.calculoimposto.domain.usecase.calculator.TaxCalculatorOrchestrationUseCase;
import br.com.ariel.calculoimposto.domain.usecase.inputparser.InputParserUseCase;
import br.com.ariel.calculoimposto.domain.usecase.outputprinter.PrintOutputUseCase;

import java.util.Scanner;

public class Main {


    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        final var orchestrator = new TaxCalculatorOrchestrationUseCase();
        final var inputParser = new InputParserUseCase();
        final var outputPrinter = new PrintOutputUseCase();

        System.out.println("\nSeja bem vindo ao sistema de calculo de taxas para venda de ações!....");

        while (true) {
            System.out.println("\n## Digite a lista de operações de COMPRA e VENDA de ações: \n");
            final String entrada = scanner.nextLine();
            final var input = inputParser.parseCommandLineInputString(entrada);
            final var calculationsResult = orchestrator.process(input);
            outputPrinter.print(calculationsResult);
        }

    }

}