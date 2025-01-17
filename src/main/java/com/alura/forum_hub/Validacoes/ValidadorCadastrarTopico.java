package com.alura.forum_hub.Validacoes;

import com.alura.forum_hub.DTO.DadosForum;
import jakarta.validation.ValidationException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorCadastrarTopico implements  ValidadorPostarNoForum{

    public void validar(DadosForum dados){
        var titulo = dados.titulo();
        var mensagem = dados.mensagem();
        var autor = dados.autor();
        var curso= dados.curso();


        if(titulo.isEmpty()){
            throw new ValidationException("Título está em branco, favor preencher.");
        }
        if(mensagem.isEmpty()) {
            throw new ValidationException("Mensagem está em branco, favor preencher.");
        }
        if(autor.isEmpty()) {
            throw new ValidationException("Autor está em branco, favor preencher.");
        }
        if(curso.isEmpty()) {
            throw new ValidationException("Curso está em branco, favor preencher.");
        }
    }
}
