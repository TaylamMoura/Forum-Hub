package com.alura.forum_hub.DTO;

import com.alura.forum_hub.Domain.Forum.Forum;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.sql.Timestamp;

@JsonPropertyOrder({"id", "titulo", "mensagem", "autor", "curso", "dataCriacao", "status"})
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
