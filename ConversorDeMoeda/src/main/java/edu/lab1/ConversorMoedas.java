package edu.lab1;


import edu.lab1.model.ConversaoRequest;
import edu.lab1.model.ConversaoResponse;
import edu.lab1.model.Moeda;
import edu.lab1.service.ExchangeRateService;
import edu.lab1.ui.MenuInterface;
import edu.lab1.util.ValidadorEntrada;

public class ConversorMoedas {
    private final MenuInterface menu;
    private final ExchangeRateService exchangeService;
    private final ValidadorEntrada validador;

    public ConversorMoedas() {
        this.menu = new MenuInterface();
        this.exchangeService = new ExchangeRateService();
        this.validador = new ValidadorEntrada();
    }

    public void iniciar() {
        menu.exibirBoasVindas();

        while (true) {
            menu.exibirMenu();
            int opcao = validador.lerOpcaoMenu();

            if (opcao == 7) {
                menu.exibirDespedida();
                break;
            }

            processarConversao(opcao);
        }
    }

    private void processarConversao(int opcao) {
        try {
            ConversaoRequest request = criarRequestConversao(opcao);
            if (request == null) return;

            ConversaoResponse response = exchangeService.realizarConversao(request);
            menu.exibirResultado(response);

        } catch (Exception e) {
            menu.exibirErro("Erro ao realizar conversão: " + e.getMessage());
        }
    }

    private ConversaoRequest criarRequestConversao(int opcao) {
        Moeda[] moedas = obterMoedasPorOpcao(opcao);
        if (moedas == null) return null;

        double valor = validador.lerValor();
        if (valor < 0) return null;

        return new ConversaoRequest(moedas[0], moedas[1], valor);
    }

    private Moeda[] obterMoedasPorOpcao(int opcao) {
        return switch (opcao) {
            case 1 -> new Moeda[]{Moeda.USD, Moeda.BRL};
            case 2 -> new Moeda[]{Moeda.BRL, Moeda.USD};
            case 3 -> new Moeda[]{Moeda.EUR, Moeda.BRL};
            case 4 -> new Moeda[]{Moeda.BRL, Moeda.EUR};
            case 5 -> new Moeda[]{Moeda.GBP, Moeda.BRL};
            case 6 -> new Moeda[]{Moeda.BRL, Moeda.GBP};
            default -> null;
        };
    }
}
