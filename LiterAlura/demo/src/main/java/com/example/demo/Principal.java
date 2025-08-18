package com.example.demo;

import com.example.demo.model.Autor;
import com.example.demo.model.Livro;
import com.example.demo.service.LiterAluraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class Principal {

    @Autowired
    private LiterAluraService service;

    private Scanner leitura = new Scanner(System.in);

    public void exibeMenu() {
        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    
                    ===============================
                    *** LITERALURA - CATÁLOGO ***
                    ===============================
                    
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    6 - Listar top 10 livros mais baixados
                    
                    0 - Sair
                    
                    ===============================
                    Escolha uma opção:
                    """;

            System.out.println(menu);

            try {
                opcao = leitura.nextInt();
                leitura.nextLine();

                switch (opcao) {
                    case 1:
                        buscarLivroPorTitulo();
                        break;
                    case 2:
                        listarLivrosRegistrados();
                        break;
                    case 3:
                        listarAutoresRegistrados();
                        break;
                    case 4:
                        listarAutoresVivosNoAno();
                        break;
                    case 5:
                        listarLivrosPorIdioma();
                        break;
                    case 6:
                        listarTop10LivrosMaisBaixados();
                        break;
                    case 0:
                        System.out.println("\nSaindo do LiterAlura... Obrigado!");
                        break;
                    default:
                        System.out.println("\nOpção inválida! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("\nEntrada inválida! Digite um número.");
                leitura.nextLine(); // Limpar buffer
            }
        }
    }

    private void buscarLivroPorTitulo() {
        System.out.print("\nDigite o nome do livro que deseja buscar: ");
        var tituloLivro = leitura.nextLine();

        try {
            Livro livro = service.buscarLivroPorTitulo(tituloLivro);
            if (livro != null) {
                System.out.println("\n*** LIVRO ENCONTRADO ***");
                System.out.println(livro);
            }
        } catch (Exception e) {
            System.out.println("\nErro ao buscar livro: " + e.getMessage());
        }
    }

    private void listarLivrosRegistrados() {
        List<Livro> livros = service.listarTodosLivros();

        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado no banco de dados.");
        } else {
            System.out.println("\n*** LIVROS REGISTRADOS ***");
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados() {
        List<Autor> autores = service.listarTodosAutores();

        if (autores.isEmpty()) {
            System.out.println("\nNenhum autor cadastrado no banco de dados.");
        } else {
            System.out.println("\n*** AUTORES REGISTRADOS ***");
            for (Autor autor : autores) {
                System.out.println(autor);
                if (autor.getLivros() != null && !autor.getLivros().isEmpty()) {
                    System.out.println("Livros: ");
                    autor.getLivros().forEach(livro ->
                            System.out.println("  - " + livro.getTitulo()));
                }
                System.out.println();
            }
        }
    }

    private void listarAutoresVivosNoAno() {
        System.out.print("\nDigite o ano que deseja pesquisar: ");
        try {
            var ano = leitura.nextInt();
            leitura.nextLine(); // Limpar buffer

            List<Autor> autores = service.listarAutoresVivosNoAno(ano);

            if (autores.isEmpty()) {
                System.out.println("\nNenhum autor vivo encontrado no ano " + ano);
            } else {
                System.out.println("\n*** AUTORES VIVOS EM " + ano + " ***");
                autores.forEach(autor -> {
                    System.out.println(autor);
                    if (autor.getLivros() != null && !autor.getLivros().isEmpty()) {
                        System.out.println("Livros: ");
                        autor.getLivros().forEach(livro ->
                                System.out.println("  - " + livro.getTitulo()));
                    }
                    System.out.println();
                });
            }
        } catch (Exception e) {
            System.out.println("\nAno inválido! Digite um número.");
            leitura.nextLine(); // Limpar buffer
        }
    }

    private void listarLivrosPorIdioma() {
        var menuIdioma = """
                
                Digite o idioma para busca:
                
                es - Espanhol
                en - Inglês
                fr - Francês
                pt - Português
                
                Idioma desejado:
                """;

        System.out.print(menuIdioma);
        var idioma = leitura.nextLine();

        List<Livro> livros = service.listarLivrosPorIdioma(idioma);

        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro encontrado no idioma: " + idioma);
        } else {
            System.out.println("\n*** LIVROS EM " + idioma.toUpperCase() + " ***");
            livros.forEach(System.out::println);
        }
    }

    private void listarTop10LivrosMaisBaixados() {
        List<Livro> livros = service.listarTop10LivrosMaisDownloads();

        if (livros.isEmpty()) {
            System.out.println("\nNenhum livro cadastrado no banco de dados.");
        } else {
            System.out.println("\n*** TOP 10 LIVROS MAIS BAIXADOS ***");
            for (int i = 0; i < livros.size(); i++) {
                System.out.println((i + 1) + "º lugar:");
                System.out.println(livros.get(i));
            }
        }
    }


}