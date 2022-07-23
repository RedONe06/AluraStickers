import java.net.URI;
import java.net.http.HttpClient; //importação da biblioteca do httpclient
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Passo 1: fazer uma conexão HTTP e buscar os top 250 filmes;

        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url); //builder

        var client = HttpClient.newHttpClient(); // conexão com o client;
        var request = HttpRequest.newBuilder(endereco).GET().build(); // pedido de request
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString()); // Segundo parâmetro cria uma classe que pode criar as
        //maneiras de ler os dados, precisa de importação;
        
        
        String body = response.body(); //retirando as informações;
        //System.out.println(body);

        // Passo 2: extrair só os dados que interessam (título, poster, clssificação) - "parsear";
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);
        System.out.println();

        // exibir e manipular os dados;
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println(filme.get("title"));
            System.out.println(filme.get("image"));
            System.out.println(filme.get("imDbRating"));
            System.out.println();
        }
    }
}
