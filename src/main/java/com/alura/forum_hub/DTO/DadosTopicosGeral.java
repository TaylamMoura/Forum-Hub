package com.alura.forum_hub.DTO;

import com.alura.forum_hub.Domain.Forum.Forum;

import java.sql.Timestamp;

public record DadosTopicosGeral(Long id,
                               String titulo,
                               String mensagem,
                               Timestamp dataCriacao) {

    public DadosTopicosGeral(Forum forum) {
            this(forum.getId(), forum.getTitulo(), forum.getMensagem(), forum.getDataCriacao());
        }
}
