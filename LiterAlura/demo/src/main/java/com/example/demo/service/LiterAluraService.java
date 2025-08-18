package com.example.demo.service;

import com.example.demo.dto.DadosLivro;
import com.example.demo.dto.DadosResposta;
import com.example.demo.model.Autor;
import com.example.demo.model.Livro;
import com.example.demo.repository.AutorRepository;
import com.example.demo.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LiterAluraService {

    private static final String ENDERECO = "https://gutendex.com/books/";

    @Autowired
    private ApiService apiService;

    @Autowired
    private ConverteDados conversor;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    public Livro buscarLivroPorTitulo(String tituloLivro) {
        String json = apiService.obterDados(ENDERECO + "?search=" +
                tituloLivro.replace(" ", "%20"));

        DadosResposta dadosResposta = conversor.obterDados(json, DadosResposta.class);

        if (dadosResposta.results() == null || dadosResposta.results().isEmpty()) {
            System.out.println("Nenhum livro encontrado com esse título!");
            return null;
        }

        DadosLivro dadosLivro = dadosResposta.results().get(0);

        // Verificar se o livro já existe no banco
        Optional<Livro> livroExistente = livroRepository.findByTituloContainingIgnoreCase(dadosLivro.titulo());
        if (livroExistente.isPresent()) {
            System.out.println("Livro já está cadastrado no banco de dados!");
            return livroExistente.get();
        }

        // Criar ou buscar autor
        Autor autor = criarOuBuscarAutor(dadosLivro);

        // Criar livro
        Livro livro = new Livro(
                dadosLivro.titulo(),
                autor,
                dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty()
                        ? dadosLivro.idiomas().get(0) : "Desconhecido",
                dadosLivro.numeroDownloads()
        );

        return livroRepository.save(livro);
    }

    private Autor criarOuBuscarAutor(DadosLivro dadosLivro) {
        if (dadosLivro.autores() == null || dadosLivro.autores().isEmpty()) {
            return criarAutorDesconhecido();
        }

        var dadosAutor = dadosLivro.autores().get(0);

        Optional<Autor> autorExistente = autorRepository.findByNome(dadosAutor.nome());
        if (autorExistente.isPresent()) {
            return autorExistente.get();
        }

        Autor novoAutor = new Autor(
                dadosAutor.nome(),
                dadosAutor.anoNascimento(),
                dadosAutor.anoMorte()
        );

        return autorRepository.save(novoAutor);
    }

    private Autor criarAutorDesconhecido() {
        Optional<Autor> autorDesconhecido = autorRepository.findByNome("Desconhecido");
        if (autorDesconhecido.isPresent()) {
            return autorDesconhecido.get();
        }

        Autor autor = new Autor("Desconhecido", null, null);
        return autorRepository.save(autor);
    }

    public List<Livro> listarTodosLivros() {
        return livroRepository.findAll();
    }

    public List<Autor> listarTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> listarAutoresVivosNoAno(Integer ano) {
        return autorRepository.findAutoresVivosNoAno(ano);
    }

    public List<Livro> listarLivrosPorIdioma(String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    public List<Livro> listarTop10LivrosMaisDownloads() {
        return livroRepository.findTop10ByOrderByNumeroDownloadsDesc();
    }
}