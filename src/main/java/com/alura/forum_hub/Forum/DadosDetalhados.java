package com.alura.forum_hub.Forum;

import java.sql.Timestamp;

public record DadosDetalhados(Long id,
                              String titulo,
                              String mensagem,
                              Timestamp dataCriacao,
                              String status,
                              String autor,
                              String curso)  {

    public DadosDetalhados(Forum forum){
        this(forum.getId(), forum.getTitulo(), forum.getMensagem(),forum.getDataCriacao(), forum.getStatus(), forum.getAutor(), forum.getCurso());
    }
}
