package edu.lab1.model;

public enum Moeda {
    USD("USD", "Dólar Americano", "$"),
    BRL("BRL", "Real Brasileiro", "R$"),
    EUR("EUR", "Euro", "€"),
    GBP("GBP", "Libra Esterlina", "£");

    private final String codigo;
    private final String nome;
    private final String simbolo;

    Moeda(String codigo, String nome, String simbolo) {
        this.codigo = codigo;
        this.nome = nome;
        this.simbolo = simbolo;
    }

    public String getCodigo() { return codigo; }
    public String getNome() { return nome; }
    public String getSimbolo() { return simbolo; }

    @Override
    public String toString() {
        return codigo;
    }
}