package com.example.demo.controller;

import com.example.demo.dto.DadosAtualizacaoUsuario;
import com.example.demo.dto.DadosCadastroUsuario;
import com.example.demo.dto.DadosDetalhamentoUsuario;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(
            @RequestBody @Valid DadosCadastroUsuario dados,
            UriComponentsBuilder uriBuilder) {

        // Verificar se login já existe
        if (repository.existsByLogin(dados.login())) {
            return ResponseEntity.badRequest().body("Login já está em uso!");
        }

        // Verificar se email já existe
        if (repository.existsByEmail(dados.email())) {
            return ResponseEntity.badRequest().body("Email já está em uso!");
        }

        // Criptografar senha
        String senhaCriptografada = passwordEncoder.encode(dados.senha());

        var usuario = new Usuario(dados.login(), senhaCriptografada, dados.nome(), dados.email());
        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping("/perfil")
    public ResponseEntity detalharPerfil(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping("/perfil")
    @Transactional
    public ResponseEntity atualizarPerfil(
            @RequestBody @Valid DadosAtualizacaoUsuario dados,
            Authentication authentication) {

        Usuario usuario = (Usuario) authentication.getPrincipal();

        // Atualizar nome se fornecido
        if (dados.nome() != null && !dados.nome().trim().isEmpty()) {
            usuario.setNome(dados.nome());
        }

        // Atualizar email se fornecido
        if (dados.email() != null && !dados.email().trim().isEmpty()) {
            // Verificar se email já está em uso por outro usuário
            if (repository.existsByEmail(dados.email()) && !usuario.getEmail().equals(dados.email())) {
                return ResponseEntity.badRequest().body("Email já está em uso!");
            }
            usuario.setEmail(dados.email());
        }

        // Atualizar senha se fornecida
        if (dados.novaSenha() != null && !dados.novaSenha().trim().isEmpty()) {
            // Verificar senha atual
            if (dados.senhaAtual() == null || !passwordEncoder.matches(dados.senhaAtual(), usuario.getSenha())) {
                return ResponseEntity.badRequest().body("Senha atual incorreta!");
            }

            String novaSenhaCriptografada = passwordEncoder.encode(dados.novaSenha());
            usuario.setSenha(novaSenhaCriptografada);
        }

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/perfil")
    @Transactional
    public ResponseEntity excluirConta(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        repository.delete(usuario);
        return ResponseEntity.noContent().build();
    }
}
