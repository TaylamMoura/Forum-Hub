package com.alura.forum_hub.Forum;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

import java.sql.Timestamp;

public record DadosForum(

        @NotBlank
        String titulo,

        @NotBlank
        String mensagem,

        Timestamp dataCriacao,

        @NotBlank
        String autor,

        @NotBlank
        String curso
) {
    public DadosForum(@NotBlank String titulo, @NotBlank String mensagem, @NotBlank String autor, @NotBlank String curso) {
        this(titulo, mensagem, new Timestamp(System.currentTimeMillis()), autor, curso);
    }

}
