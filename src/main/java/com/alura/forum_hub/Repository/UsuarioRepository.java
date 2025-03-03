package com.alura.forum_hub.Repository;

import com.alura.forum_hub.Domain.Usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long> {

    UserDetails findByLogin(String login);
}
