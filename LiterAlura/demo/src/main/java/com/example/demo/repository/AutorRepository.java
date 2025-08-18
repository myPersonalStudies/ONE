package com.example.demo.repository;

import com.example.demo.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    Optional<Autor> findByNome(String nome);

    // Derived Query para autores vivos em determinado ano
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoMorteGreaterThanEqualOrAnoMorteIsNull(
            Integer anoNascimento, Integer anoMorte);

    // Query personalizada como backup
    @Query("SELECT a FROM Autor a WHERE :ano >= a.anoNascimento AND (:ano <= a.anoMorte OR a.anoMorte IS NULL)")
    List<Autor> findAutoresVivosNoAno(@Param("ano") Integer ano);

    // Derived queries para funcionalidades extras
    List<Autor> findByNomeContainingIgnoreCase(String nome);

    List<Autor> findByAnoNascimentoBetween(Integer anoInicio, Integer anoFim);

    List<Autor> findByAnoMorteIsNull(); // Autores ainda vivos

    List<Autor> findByAnoNascimentoLessThan(Integer ano);

    List<Autor> findByAnoMorteGreaterThan(Integer ano);
}