package edu.lab1.ui;

import edu.lab1.model.ConversaoResponse;
import edu.lab1.model.Moeda;

import java.time.format.DateTimeFormatter;

public class MenuInterface {
    private static final String SEPARADOR = "=================================";

    public void exibirBoasVindas() {
        System.out.println(SEPARADOR);
        System.out.println("🌍 CONVERSOR DE MOEDAS 🌍");
        System.out.println("   Desenvolvido com Java POO");
        System.out.println(SEPARADOR);
        System.out.println();
    }

    public void exibirMenu() {
        System.out.println("📋 Escolha uma opção de conversão:");
        System.out.println("1) 💵 Dólar Americano → Real Brasileiro");
        System.out.println("2) 💰 Real Brasileiro → Dólar Americano");
        System.out.println("3) 💶 Euro → Real Brasileiro");
        System.out.println("4) 💰 Real Brasileiro → Euro");
        System.out.println("5) 💷 Libra Esterlina → Real Brasileiro");
        System.out.println("6) 💰 Real Brasileiro → Libra Esterlina");
        System.out.println("7) 🚪 Sair");
        System.out.print("➤ Digite sua opção: ");
    }

    public void exibirResultado(ConversaoResponse response) {
        System.out.println();
        System.out.println("✅ CONVERSÃO REALIZADA COM SUCESSO!");
        System.out.println("─".repeat(40));

        Moeda origem = response.getRequest().getMoedaOrigem();
        Moeda destino = response.getRequest().getMoedaDestino();

        System.out.printf("💰 %s %.2f (%s) = %s %.2f (%s)%n",
                origem.getSimbolo(),
                response.getRequest().getValor(),
                origem.getCodigo(),
                destino.getSimbolo(),
                response.getValorConvertido(),
                destino.getCodigo());

        System.out.printf("📊 Taxa de câmbio: 1 %s = %.6f %s%n",
                origem.getCodigo(),
                response.getTaxaConversao(),
                destino.getCodigo());

        System.out.printf("🕐 Horário da consulta: %s%n",
                response.getTimestamp().format(
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));

        System.out.println();
    }

    public void exibirErro(String mensagem) {
        System.out.println("❌ " + mensagem);
        System.out.println();
    }

    public void exibirDespedida() {
        System.out.println();
        System.out.println("🙏 Obrigado por usar o Conversor de Moedas!");
        System.out.println("💡 Desenvolvido com Java POO + Maven");
        System.out.println("👋 Até a próxima!");
    }
}