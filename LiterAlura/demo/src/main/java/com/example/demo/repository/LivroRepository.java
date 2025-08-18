package com.example.demo.repository;

import com.example.demo.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    Optional<Livro> findByTituloContainingIgnoreCase(String titulo);

    // Derived queries para idiomas
    List<Livro> findByIdioma(String idioma);

    Long countByIdioma(String idioma);

    // Derived queries para funcionalidades extras
    List<Livro> findTop10ByOrderByNumeroDownloadsDesc();

    List<Livro> findByAutor_Nome(String nomeAutor);

    List<Livro> findByAutor_NomeContainingIgnoreCase(String nomeAutor);

    List<Livro> findByNumeroDownloadsGreaterThan(Double numeroDownloads);

    @Query("SELECT DISTINCT l.idioma FROM Livro l")
    List<String> findAllDistinctIdiomas();

    // Query para estatísticas gerais
    @Query("SELECT COUNT(l) FROM Livro l")
    Long countTotalLivros();
}