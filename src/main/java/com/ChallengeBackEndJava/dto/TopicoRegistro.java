package com.ChallengeBackEndJava.dto;

import jakarta.validation.constraints.NotNull;

public record TopicoRegistro(

        Long id,
        @NotNull String titulo,
        @NotNull String mensaje,
        //@Null
        //LocalDate fechadecreación,
        //@Null
        //boolean estado,
        @NotNull String autor,
        @NotNull String curso) {
}
