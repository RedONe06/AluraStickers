import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

public class ClienteHTTP {
    public String buscaDados(String url) {

        try {
            URI endereco = URI.create(url); // builder
            var client = HttpClient.newHttpClient(); // conexão com o client;
            var request = HttpRequest.newBuilder(endereco).GET().build(); // pedido de request
            HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
            /*
             * Segundo parâmetro cria uma classe que pode criar as maneiras de ler os dados,
             * precisa de importação;
             */
            String body = response.body(); // retirando as informações;
            return body;
        } catch (IOException | InterruptedException ex) {
            throw new RuntimeException(ex);
        }

    }
}
