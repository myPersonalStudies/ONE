package edu.lab1.model;

public class ConversaoRequest {
    private final Moeda moedaOrigem;
    private final Moeda moedaDestino;
    private final double valor;

    public ConversaoRequest(Moeda moedaOrigem, Moeda moedaDestino, double valor) {
        this.moedaOrigem = moedaOrigem;
        this.moedaDestino = moedaDestino;
        this.valor = valor;
    }

    public Moeda getMoedaOrigem() { return moedaOrigem; }
    public Moeda getMoedaDestino() { return moedaDestino; }
    public double getValor() { return valor; }
}