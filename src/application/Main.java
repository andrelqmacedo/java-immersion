package application;

import entities.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {

        //Fazer uma conexão HTTP (GET) e buscar as spells
        String url = "https://api.open5e.com/spells/?format=json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response =client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        System.out.println(body);

        //Extrair/parsear os dados que interessam (Nome, Imagem, Poder)
        JsonParser parser = new JsonParser();
        List<Map<String, String>> spells = parser.parse(body);

        //Exibir e manipular os dados
        for(Map<String, String> spell : spells) {
            System.out.println(spell.get("name"));
            System.out.println(spell.get("desc"));
            System.out.println(spell.get("range"));
            System.out.println(spell.get("components"));
            System.out.println(spell.get("casting_time"));
            System.out.println(spell.get("level"));
            System.out.println(spell.get("dnd_class"));
            System.out.println();

        }

    }
}