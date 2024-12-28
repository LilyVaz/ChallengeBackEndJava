package com.ChallengeBackEndJava.dto;

import jakarta.validation.constraints.NotNull;

public record TopicoActualizar(
    @NotNull
    String titulo,
    @NotNull
    String mensaje,
    @NotNull
    String autor,
    @NotNull
    String curso
) {
    
}