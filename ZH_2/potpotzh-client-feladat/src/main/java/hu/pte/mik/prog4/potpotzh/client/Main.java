package hu.pte.mik.prog4.potpotzh.client;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    // A REST API alap URL-je (Ellenőrizd, hogy a Tomcaten is ezen a porton/néven fut-e!)
    private static final String BASE_URL = "http://localhost:8080/potpotzh_feladat_war_exploded/api/company";

    public static void main(String[] args) {
        System.out.println("--- REST API Kliens Indítása ---\n");

        HttpClient client = HttpClient.newHttpClient();

        try {
            // 1. Összes cég lekérése (Listázó végpont)
            System.out.println("1. Összes cég lekérése (/api/company):");
            HttpRequest requestAll = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();
            HttpResponse<String> responseAll = client.send(requestAll, HttpResponse.BodyHandlers.ofString());
            System.out.println("Válasz kód: " + responseAll.statusCode());
            System.out.println("Válasz test: " + responseAll.body() + "\n");

            // 2. Egy cég lekérése ID alapján (Teszt adat: 1 - Apple)
            long testId = 1L;
            System.out.println("2. Cég lekérése ID alapján (/api/company/" + testId + "):");
            HttpRequest requestById = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + testId))
                    .GET()
                    .build();
            HttpResponse<String> responseById = client.send(requestById, HttpResponse.BodyHandlers.ofString());
            System.out.println("Válasz kód: " + responseById.statusCode());
            System.out.println("Válasz test: " + responseById.body() + "\n");

            // 3. Eladott termékek számának lekérése (Ami a WS-t hívja a háttérben)
            System.out.println("3. Eladott termékek száma WS-ből (/api/company/" + testId + "/sold-products):");
            HttpRequest requestSoldProducts = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/" + testId + "/sold-products"))
                    .GET()
                    .build();
            HttpResponse<String> responseSoldProducts = client.send(requestSoldProducts, HttpResponse.BodyHandlers.ofString());
            System.out.println("Válasz kód: " + responseSoldProducts.statusCode());
            System.out.println("Válasz test: " + responseSoldProducts.body() + "\n");

        } catch (Exception e) {
            System.err.println("Hiba történt a REST API hívása során!");
            e.printStackTrace();
        }

        System.out.println("--- REST API Kliens Leállt ---");
    }
}