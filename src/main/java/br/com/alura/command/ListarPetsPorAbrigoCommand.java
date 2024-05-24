package br.com.alura.command;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ListarPetsPorAbrigoCommand implements Command{
    @Override
    public void execute() {
        try {
            var client = new ClientHttpConfiguration();
            PetService petService = new PetService(client);
            petService.listarPetsPorAbrigo();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
