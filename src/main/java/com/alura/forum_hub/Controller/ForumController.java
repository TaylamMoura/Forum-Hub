package com.alura.forum_hub.Controller;


import com.alura.forum_hub.Forum.DadosAtualizadosTopicos;
import com.alura.forum_hub.Forum.DadosForum;
import com.alura.forum_hub.Forum.DadosTopicos;
import com.alura.forum_hub.Forum.Forum;
import com.alura.forum_hub.Repository.ForumRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class ForumController {

    @Autowired
    private ForumRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosForum  dados){
        if(repository.existsByTitulo(dados.titulo())){
            System.out.println("Topico já cadastrado.");
        }
        if(repository.existsByMensagem(dados.mensagem())){
            System.out.println("Mensagem já cadastrada");
        }
        Forum forum = new Forum(dados);
        Forum forumSalvo = repository.save(forum);
    }

    @GetMapping
    public Page<DadosTopicos> listarTopicos(Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosTopicos::new);
    }

    @GetMapping("/{id}")
    public Optional<DadosTopicos> listarTopicoUnico(@PathVariable Long id) {
        return repository.findById(id).map(DadosTopicos::new);
    }

    @PutMapping("/{id}")
    @Transactional
    public void atualizar(@PathVariable Long id, @RequestBody @Valid  DadosAtualizadosTopicos dados){
        Optional<Forum> optionalForum = repository.findById(id);
        if (optionalForum.isPresent()){
            var atualizarDados = optionalForum.get();
            atualizarDados.atualizar(dados);
            }
        }

    @DeleteMapping("/{id}")
    @Transactional
    public void deletar(@PathVariable Long id){
        Optional<Forum> optionalForum = repository.findById(id);
        if (optionalForum.isPresent()){
            repository.deleteById(id);
        }
    }
}
