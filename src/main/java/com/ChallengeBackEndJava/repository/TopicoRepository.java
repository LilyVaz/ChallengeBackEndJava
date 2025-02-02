package com.ChallengeBackEndJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ChallengeBackEndJava.modelo.Topico;


public interface TopicoRepository extends JpaRepository<Topico, Long> {
   //Page<Topico> findByAll(Pageable paginacion);

}