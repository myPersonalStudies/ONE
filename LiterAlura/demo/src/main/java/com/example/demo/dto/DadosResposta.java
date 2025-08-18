package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record DadosResposta(
        @JsonProperty("count") Integer count,
        @JsonProperty("next") String next,
        @JsonProperty("previous") String previous,
        @JsonProperty("results") List<DadosLivro> results
) {}








