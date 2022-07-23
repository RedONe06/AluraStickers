import java.util.List;

public interface ExtratorDeConteudo {
    // em uma interface tudo é público
    public List<Conteudo> extraiConteudos(String json);

}
