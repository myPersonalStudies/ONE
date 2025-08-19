package com.example.demo.dto;

import com.example.demo.model.Usuario;

public record DadosDetalhamentoUsuario(
        Long id,
        String login,
        String nome,
        String email
) {
    public DadosDetalhamentoUsuario(Usuario usuario) {
        this(usuario.getId(), usuario.getLogin(), usuario.getNome(), usuario.getEmail());
    }
}
