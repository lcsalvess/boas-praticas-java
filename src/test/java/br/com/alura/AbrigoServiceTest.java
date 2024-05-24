package br.com.alura;


import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import br.com.alura.service.AbrigoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbrigoServiceTest {
    private ClientHttpConfiguration clientHttpConfiguration = mock(ClientHttpConfiguration.class);
    private AbrigoService abrigoService = new AbrigoService(clientHttpConfiguration);
    private HttpResponse<String> response = mock(HttpResponse.class);
    private Abrigo abrigo = new Abrigo("Teste", "11999446678", "teste@gmail.com");

    @Test
    @DisplayName("Verificar se requisição GET será chamada")
    public void requisicao_cenario1() throws IOException, InterruptedException {
        abrigo.setId(0L);
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdENome = "0 - Teste";

        //essa classe armazena os dados em um ARRAY
        //de bytes na memória
        //tem tamanho dinâmico (o tamanho do array de bytes
        //é ajustado automaticamente à medida que novos dados
        //são adicionados
        //em TESTES UNITÁRIOS, é MUITO ÚTIL: permite capturar a
        //saída de um método ou classe e verificar se
        //o resultado está correto
        //no teste abaixo, ela está sendo usada para capturar a
        //saída impressa pelo método listarAbrigo() da classe
        //AbrigoService. Isso permite que o teste verifique
        //se a saída impressa contém os valores esperados
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        //quando o método body() for chamado no objeto response, o Mockito
        //irá retornar a string, que representa um array JSON contendo um
        //único objeto Abrigo
        when(response.body()).thenReturn("[{"+abrigo.toString()+"}]");
        //o Mockito está configurando o comportamento esperado do método
        //dispararRequisicaoGet() do objeto client. Quando esse método
        //for chamado com qualquer string como argumento (usando o
        //anyString()), o Mockito irá retornar o objeto response previamente
        //configurado.
        when(clientHttpConfiguration.dispararRequisicaoGet(anyString())).thenReturn(response);

        abrigoService.listarAbrigo();

        //Aqui, o código está capturando a saída impressa pelo método
        // listarAbrigo() usando a ByteArrayOutputStream
        // (que foi configurada anteriormente).
        // O conteúdo da ByteArrayOutputStream é convertido para uma
        // string e, em seguida, dividido em linhas usando o separador
        // de linha do sistema operacional (System.lineSeparator())
        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdENome = lines[1];

        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectedIdENome, actualIdENome);

    }





}
