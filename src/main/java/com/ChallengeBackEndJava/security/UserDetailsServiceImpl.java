package com.ChallengeBackEndJava.security;

import com.ChallengeBackEndJava.modelo.Usuario;
import com.ChallengeBackEndJava.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword1())
                .roles(usuario.getRole()) // Asignar roles
                .build();
    }
    /*@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

    // Imprimir valores del usuario cargado desde la base de datos
    System.out.println("Usuario encontrado: ");
    System.out.println("Username: " + usuario.getUsername());
    System.out.println("Password (cifrado en BD): " + usuario.getPassword1());
    System.out.println("Rol: " + usuario.getRole());

    return User.builder()
            .username(usuario.getUsername())
            .password(usuario.getPassword1()) // Asegúrate de usar el campo correcto
            .roles(usuario.getRole())
            .build();
}*/
}
