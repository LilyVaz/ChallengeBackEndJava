package com.ChallengeBackEndJava.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ChallengeBackEndJava.dto.TopicoActualizar;
import com.ChallengeBackEndJava.dto.TopicoLista;
import com.ChallengeBackEndJava.dto.TopicoRegistro;
import com.ChallengeBackEndJava.exceptions.RecursoNoEncontradoException;
import com.ChallengeBackEndJava.modelo.Topico;
import com.ChallengeBackEndJava.repository.TopicoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public void topicoRegistro(@RequestBody @Valid TopicoRegistro topicoRegistro) {
        topicoRepository.save(new Topico(topicoRegistro));
    }

    @GetMapping
    public List<TopicoLista> listar() {
        return topicoRepository.findAll().stream()
                .map(TopicoLista::new)
                .toList();
    }

    @GetMapping("{id}")
    public TopicoLista obtenerDetalle(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado con id: " + id));
        return new TopicoLista(topico);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> actualizarTopico(
            @PathVariable Long id,
            @RequestBody @Valid TopicoActualizar topicoActualizar) {

        // Verificar si el tópico existe
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {
            throw new RecursoNoEncontradoException("Tópico no encontrado con id: " + id);
        }

        // Actualizar los datos del tópico existente
        Topico topico = topicoOptional.get();
        topico.setTitulo(topicoActualizar.titulo());
        topico.setMensaje(topicoActualizar.mensaje());
        topico.setAutor(topicoActualizar.autor());
        topico.setCurso(topicoActualizar.curso());

        // Guardar los cambios en la base de datos
        topicoRepository.save(topico);

        // Retornar respuesta HTTP 204 (No Content) indicando éxito sin contenido en el
        // cuerpo
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        // Verificar si el tópico existe
        if (!topicoRepository.existsById(id)) {
            throw new RecursoNoEncontradoException("Tópico no encontrado con id: " + id);
        }

        // Eliminar el tópico
        topicoRepository.deleteById(id);

        // Retornar respuesta HTTP 204 (No Content)
        return ResponseEntity.noContent().build();
    }

}
