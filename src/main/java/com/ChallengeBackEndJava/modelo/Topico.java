package com.ChallengeBackEndJava.modelo;

import java.time.LocalDate;

import com.ChallengeBackEndJava.dto.TopicoRegistro;

import jakarta.persistence.*;
import lombok.*;


@Entity(name="Topico")
@Table(name="Topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")

public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDate fechadecreación;
    private boolean estado;
    private String autor;
    private  String curso;    
    
    public  Topico (TopicoRegistro topicoRegistro){
        this.id = topicoRegistro.id();
        this.titulo =  topicoRegistro.titulo();
        this.mensaje = topicoRegistro.mensaje();
        //this.fechadecreación  = topicoRegistro.fechadecreación();
        //this.estado = topicoRegistro.estado();
        this.autor = topicoRegistro.autor();
        this.curso = topicoRegistro.curso();
    }

}
