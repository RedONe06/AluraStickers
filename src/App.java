import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        // Entrada das especificações da API:
        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();
        
        //String url = "https://api.nasa.gov/planetary/apod?api_key=cgQUw0qhiSsCWWNJ9rk19ldiATbbQAtCM2axYZzI&start_date=2022-06-12&end_date=2022-06-14";
        //ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();

        // Coneção com o clienteHTTP:
        var http = new ClienteHTTP();
        String json = http.buscaDados(url);
        
        // Parser:
        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        // Gerar as figurinhas:
        var geradora = new GeradoraDeFigurinhas();
        
        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);
            // Inserindo a url do for na entrada
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            // Ajustando o nome do arquivo de saída
            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";
            // Gerando a figurinha
            geradora.cria(inputStream, nomeArquivo);

            // Output
            System.out.println(conteudo.getTitulo());
            System.out.println();
        }
    }
}
