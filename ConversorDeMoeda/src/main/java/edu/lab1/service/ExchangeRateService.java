package edu.lab1.service;

import com.google.gson.Gson;
import edu.lab1.ApiConfig;
import edu.lab1.model.ApiResponse;
import edu.lab1.model.ConversaoRequest;
import edu.lab1.model.ConversaoResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateService {
    private final HttpClient httpClient;
    private final Gson gson;
    private final String apiKey;

    public ExchangeRateService() {
        this.httpClient = HttpClient.newHttpClient();
        this.gson = new Gson();
        this.apiKey = ApiConfig.getApiKey();
    }

    public ConversaoResponse realizarConversao(ConversaoRequest request)
            throws IOException, InterruptedException {

        double taxaConversao = obterTaxaConversao(
                request.getMoedaOrigem().getCodigo(),
                request.getMoedaDestino().getCodigo()
        );

        return new ConversaoResponse(request, taxaConversao);
    }

    private double obterTaxaConversao(String moedaOrigem, String moedaDestino)
            throws IOException, InterruptedException {

        String url = construirUrl(moedaOrigem, moedaDestino);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("User-Agent", "Conversor-Moedas/1.0")
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request,
                HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("Erro HTTP: " + response.statusCode());
        }

        ApiResponse apiResponse = gson.fromJson(response.body(), ApiResponse.class);

        if (!apiResponse.isSuccess()) {
            throw new RuntimeException("Erro da API: " + apiResponse.getError_type());
        }

        return apiResponse.getConversion_rate();
    }

    private String construirUrl(String moedaOrigem, String moedaDestino) {
        return String.format("%s%s/pair/%s/%s",
                ApiConfig.BASE_URL, apiKey, moedaOrigem, moedaDestino);
    }
}