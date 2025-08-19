package com.example.demo.repository;

import com.example.demo.ENUM.StatusTopico;
import com.example.demo.model.Topico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findByStatusOrderByDataCriacaoDesc(StatusTopico status, Pageable pageable);
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
}