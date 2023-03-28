package application;

import entities.GeradorFigurinha;
import entities.JsonParser;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP (GET) e buscar os filmes
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //Extrair/parsear os dados que interessam (Nome, Imagem, Poder)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir e manipular os dados
        var geradora = new GeradorFigurinha();
        for(Map<String, String> filmes : listaDeFilmes) {
            String urlImagem = filmes.get("image");
            String titulo = filmes.get("title");
            InputStream inputStream = new URL(urlImagem).openStream();

            String nomeArquivo = titulo + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(titulo);
            System.out.println();

        }

    }
}
