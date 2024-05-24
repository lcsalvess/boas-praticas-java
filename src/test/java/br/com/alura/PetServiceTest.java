package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.PetService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PetServiceTest {
    private ClientHttpConfiguration clientHttpConfiguration = mock(ClientHttpConfiguration.class);
    private PetService petService = new PetService(clientHttpConfiguration);
    private HttpResponse<String> response = mock(HttpResponse.class);
    @Test
    @DisplayName("Verificar se ao disparar requisição POST será chamado")
    public void requisicao_cenario1() throws IOException, InterruptedException {
        String userInput = String.format("Teste%spets.csv",
                System.lineSeparator());
        ByteArrayInputStream bais = new ByteArrayInputStream(userInput.getBytes());
        System.setIn(bais);

        when(clientHttpConfiguration.dispararRequisicaoPost(anyString(),any())).thenReturn(response);

        petService.importarPetsDoAbrigo();
        verify(clientHttpConfiguration.dispararRequisicaoPost(anyString(), anyString()), atLeast(1));

    }
}
