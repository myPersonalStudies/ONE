package com.example.demo.controller;

import com.example.demo.dto.DadosAtualizacaoTopico;
import com.example.demo.dto.DadosCadastroTopico;
import com.example.demo.dto.DadosDetalhamentoTopico;
import com.example.demo.dto.DadosListagemTopico;
import com.example.demo.model.Topico;
import com.example.demo.model.Usuario;
import com.example.demo.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastroTopico dados,
            Authentication authentication,
            UriComponentsBuilder uriBuilder) {

        // Verificar duplicatas
        if (repository.existsByTituloAndMensagem(dados.titulo(), dados.mensagem())) {
            return ResponseEntity.badRequest().body("Tópico duplicado!");
        }

        Usuario autor = (Usuario) authentication.getPrincipal();
        Topico topico = new Topico(dados.titulo(), dados.mensagem(), autor, dados.curso());

        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10) Pageable paginacao) {

        var page = repository.findAll(paginacao)
                .map(DadosListagemTopico::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizacaoTopico dados,
            Authentication authentication) {

        var topico = repository.getReferenceById(id);

        // Verificar se é o autor
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(403).body("Apenas o autor pode editar o tópico");
        }

        if (dados.titulo() != null) {
            topico.setTitulo(dados.titulo());
        }
        if (dados.mensagem() != null) {
            topico.setMensagem(dados.mensagem());
        }
        if (dados.curso() != null) {
            topico.setCurso(dados.curso());
        }

        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id, Authentication authentication) {
        var topico = repository.getReferenceById(id);

        // Verificar se é o autor
        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
        if (!topico.getAutor().getId().equals(usuarioLogado.getId())) {
            return ResponseEntity.status(403).body("Apenas o autor pode excluir o tópico");
        }

        repository.delete(topico);
        return ResponseEntity.noContent().build();
    }
}