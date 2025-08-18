package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonProperty;

public record DadosAutor(
        @JsonProperty("name") String nome,
        @JsonProperty("birth_year") Integer anoNascimento,
        @JsonProperty("death_year") Integer anoMorte
) {}