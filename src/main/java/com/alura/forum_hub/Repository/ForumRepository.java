package com.alura.forum_hub.Repository;

import com.alura.forum_hub.Domain.Forum.Forum;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Long> {

    boolean existsByTitulo(String titulo);
    boolean existsByMensagem(String mensagem);
}
