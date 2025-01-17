package com.alura.forum_hub.Validacoes;

import com.alura.forum_hub.DTO.DadosAtualizadosTopicos;
import org.springframework.stereotype.Component;

@Component
public interface ValidadorAtualizarTopico {
    void validar(DadosAtualizadosTopicos dados);
}
