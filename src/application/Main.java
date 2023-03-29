package application;

import entities.*;

import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP (GET) e buscar os filmes (corpo da resposta)
        String url = "https://api.nasa.gov/planetary/apod?api_key=KfYDXwlIf7vpHmCLsfqCgdRaOLyGUXVG7CUotIr1";
        ExtratorConteudoNasa extrator = new ExtratorConteudoNasa();

        var http = new ClientHttp();
        String json = http.buscaDados(url);


        //Exibir e manipular os dados

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradorFigurinha();

        for(int i=0; i<3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();

            String nomeArquivo = "saida/" + conteudo.getTitulo() + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.getTitulo());
            System.out.println();

        }

    }
}
