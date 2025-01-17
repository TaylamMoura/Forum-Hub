package com.alura.forum_hub.Validacoes;

import com.alura.forum_hub.DTO.DadosForum;
import com.alura.forum_hub.Repository.ForumRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorTopicoDuplicado implements ValidadorPostarNoForum{

    @Autowired
    private ForumRepository repository;


    @Override
    public void validar(DadosForum dados){

        if(repository.existsByTitulo(dados.titulo())){
            throw new ValidationException("Titulo já cadastrado");
        }
        if(repository.existsByMensagem(dados.mensagem())){
            throw new ValidationException("Mensagem já cadastrada!");
        }
    }
}
