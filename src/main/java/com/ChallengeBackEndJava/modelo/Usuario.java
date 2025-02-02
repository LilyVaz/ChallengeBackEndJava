package com.ChallengeBackEndJava.modelo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "username", nullable = false, length = 15)
    private String username;

    @Column(name = "password1", nullable = false, length = 60)
    private String password1;

    @Column(nullable = false, length = 20)
    private String role;

    @Column(nullable = false)
    private boolean estado; // Puedes mapear tinyint como boolean
}

