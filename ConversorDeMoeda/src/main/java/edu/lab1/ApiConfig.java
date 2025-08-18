package edu.lab1;

public class ApiConfig {
    public static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final String API_KEY = "SUA_API_KEY_AQUI"; // ⚠️ SUBSTITUA PELA SUA CHAVE

    public static String getApiKey() {
        if ("SUA_API_KEY_AQUI".equals(API_KEY)) {
            throw new IllegalStateException(
                    "⚠️ Configure sua API Key em ApiConfig.java! " +
                            "Obtenha em: https://www.exchangerate-api.com");
        }
        return API_KEY;
    }

    // Método alternativo para ler de variável de ambiente
    public static String getApiKeyFromEnv() {
        String key = System.getenv("EXCHANGE_RATE_API_KEY");
        if (key == null || key.trim().isEmpty()) {
            throw new IllegalStateException(
                    "⚠️ Configure a variável de ambiente EXCHANGE_RATE_API_KEY");
        }
        return key;
    }
}