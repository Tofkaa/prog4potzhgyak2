package hu.pte.mik.prog4.potpotzh.client;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    // A Tomcat URL-ed és a web.xml mapping alapján. Ha más a context path (pl. potpotzh_feladat_war_exploded), azt írd át!
    private static final String BASE_URL = "http://localhost:8080/potpotzh_feladat_war_exploded/api/company";

    public static void main(String[] args) {
        System.out.println("---- POTPOTZH REST KLIENS ----\n");

        HttpClient client = HttpClient.newHttpClient();

        try {
            // 1. ÖSSZES CÉG LISTÁZÁSA (GET /company)
            System.out.println("1. Összes cég listázása...");
            HttpRequest listRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            HttpResponse<String> listResponse = client.send(listRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Státusz: " + listResponse.statusCode());
            System.out.println("Válasz: " + listResponse.body() + "\n");

            // 2. EGY KONKRÉT CÉG LEKÉRDEZÉSE (GET /company/{id})
            System.out.println("2. Cég lekérdezése (ID = 1)...");
            HttpRequest getByIdRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/1"))
                    .GET()
                    .build();

            HttpResponse<String> byIdResponse = client.send(getByIdRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Státusz: " + byIdResponse.statusCode());
            System.out.println("Válasz: " + byIdResponse.body() + "\n");

            // 3. ELADOTT TERMÉKEK (WS ADAT) LEKÉRDEZÉSE (GET /company/{id}/getdata)
            System.out.println("3. Eladott termékek (WS) lekérése (ID = 1)...");
            HttpRequest wsRequest = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/1/getdata"))
                    .GET()
                    .build();

            HttpResponse<String> wsResponse = client.send(wsRequest, HttpResponse.BodyHandlers.ofString());
            System.out.println("Státusz: " + wsResponse.statusCode());
            System.out.println("Válasz: " + wsResponse.body() + "\n");

        } catch (Exception e) {
            System.out.println("Hiba a REST API hívása során:");
            e.printStackTrace();
        }
    }
}