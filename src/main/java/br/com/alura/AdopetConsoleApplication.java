package br.com.alura;

import br.com.alura.command.*;
import br.com.alura.command.abrigo.CadastrarAbrigoCommand;
import br.com.alura.command.abrigo.ListarAbrigoCommand;
import br.com.alura.command.pets.ImportarPetsCommand;
import br.com.alura.command.pets.ListarPetsPorAbrigoCommand;

import java.util.Scanner;

public class AdopetConsoleApplication {

    public static void main(String[] args) {
        CommandExecutor executor = new CommandExecutor();
        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");
        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                exibeMenu();
                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                switch (opcaoEscolhida) {
                    case 1:
                        executor.executeCommand(new ListarAbrigoCommand());
                        break;
                    case 2:
                        executor.executeCommand(new CadastrarAbrigoCommand());
                        break;
                    case 3:
                        executor.executeCommand(new ListarPetsPorAbrigoCommand());
                        break;
                    case 4:
                        executor.executeCommand(new ImportarPetsCommand());
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Opção inválida! Tente novamente");
                        opcaoEscolhida = 0;
                }
            }
            System.out.println("Finalizando o programa...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exibeMenu() {
        System.out.println("\nDIGITE O NÚMERO DA OPERAÇÃO DESEJADA:");
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }
}
