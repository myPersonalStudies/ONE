package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record DadosLivro(
        @JsonProperty("id") Integer id,
        @JsonProperty("title") String titulo,
        @JsonProperty("authors") List<DadosAutor> autores,
        @JsonProperty("languages") List<String> idiomas,
        @JsonProperty("download_count") Double numeroDownloads
) {}
