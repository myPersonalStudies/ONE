package edu.lab1.model;

import java.time.LocalDateTime;

public class ConversaoResponse {
    private final ConversaoRequest request;
    private final double taxaConversao;
    private final double valorConvertido;
    private final LocalDateTime timestamp;

    public ConversaoResponse(ConversaoRequest request, double taxaConversao) {
        this.request = request;
        this.taxaConversao = taxaConversao;
        this.valorConvertido = request.getValor() * taxaConversao;
        this.timestamp = LocalDateTime.now();
    }

    public ConversaoRequest getRequest() { return request; }
    public double getTaxaConversao() { return taxaConversao; }
    public double getValorConvertido() { return valorConvertido; }
    public LocalDateTime getTimestamp() { return timestamp; }
}

