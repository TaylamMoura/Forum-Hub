package com.alura.forum_hub.Validacoes;

import com.alura.forum_hub.DTO.DadosAtualizadosTopicos;
import com.alura.forum_hub.Domain.Forum.Forum;
import com.alura.forum_hub.Repository.ForumRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoAtualizado implements ValidadorAtualizarTopico {

    @Autowired
    private ForumRepository repository;


    @Override
    public void validar(DadosAtualizadosTopicos dados) {

        Forum forumExistente = repository.findById(dados.id())
                .orElseThrow(() -> new ValidationException("Tópico não encontrado."));
        if (dados.titulo() != null && !dados.titulo().equals(forumExistente.getTitulo()) && repository.existsByTitulo(dados.titulo())) {
            throw new ValidationException("Título já cadastrado.");
        }
        if (dados.mensagem() != null && !dados.mensagem().equals(forumExistente.getMensagem()) && repository.existsByMensagem(dados.mensagem())) {
            throw new ValidationException("Mensagem já cadastrada!");
        }
    }
}
