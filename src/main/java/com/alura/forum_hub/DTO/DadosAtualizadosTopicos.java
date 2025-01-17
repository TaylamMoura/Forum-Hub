package com.alura.forum_hub.DTO;

public record DadosAtualizadosTopicos(
        Long id,
        String titulo,
        String mensagem,
        String autor,
        String curso
) {
}
