package com.example.demo.dto;

import jakarta.validation.constraints.Email;

public record DadosAtualizacaoUsuario(
        String nome,

        @Email(message = "Email deve ter formato válido")
        String email,

        String senhaAtual,
        String novaSenha
) {}