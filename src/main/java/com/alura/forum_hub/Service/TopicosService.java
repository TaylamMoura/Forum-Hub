package com.alura.forum_hub.Service;

import com.alura.forum_hub.Repository.UsuarioRepository;
import com.alura.forum_hub.DTO.DadosAtualizadosTopicos;
import com.alura.forum_hub.DTO.DadosForum;
import com.alura.forum_hub.Domain.Forum.Forum;
import com.alura.forum_hub.Validacoes.ValidadorAtualizarTopico;
import com.alura.forum_hub.Validacoes.ValidadorPostarNoForum;
import com.alura.forum_hub.Repository.ForumRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class TopicosService {

    @Autowired
    private ForumRepository forumRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private List<ValidadorPostarNoForum> validador;

    @Autowired
    private List<ValidadorAtualizarTopico> validadorAtualizar;


    @Transactional
    public Forum cadastrarTopico(DadosForum dados) {
        validador.forEach(v -> v.validar(dados));

        var forum = new Forum(dados);
        forumRepository.save(forum);

        return forum;
    }

    @Transactional
    public Forum atualizarTopico(Long id, DadosAtualizadosTopicos dados) {

        validadorAtualizar.forEach((v -> v.validar(new DadosAtualizadosTopicos(id, dados.titulo(), dados.mensagem(), dados.autor(), dados.curso()))));

        Optional<Forum> optionalForum = forumRepository.findById(id);
        if (optionalForum.isPresent()) {
            var forum = optionalForum.get();
            forum.atualizar(dados);
            forumRepository.save(forum);
            return forum;
        } else {
            throw new ValidationException("Tópico não atualizado");
        }
    }


    @Transactional
    public void deletarTopico(Long id) {
        Optional<Forum> optionalForum = forumRepository.findById(id);
        if (optionalForum.isPresent()) {
            forumRepository.deleteById(id);
        } else {
            throw new ValidationException("Não foi possivel realizar a exclusão");
        }
    }
}




