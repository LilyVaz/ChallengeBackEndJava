package com.ChallengeBackEndJava.dto;

import java.time.LocalDate;

import com.ChallengeBackEndJava.modelo.Topico;

public record TopicoLista(Long id,
String titulo,
String mensaje,
LocalDate fechacreacion,
boolean estado,
String autor,
String curso
) {
public TopicoLista(Topico topico) {
    this(
        topico.getId(),
        topico.getTitulo(),
        topico.getMensaje(),
        topico.getFechadecreación(),
        topico.isEstado(),
        topico.getAutor(),
        topico.getCurso()
    );
    
}
}
