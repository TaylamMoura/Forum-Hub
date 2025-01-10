package com.alura.forum_hub.Controller;


import com.alura.forum_hub.Forum.*;
import com.alura.forum_hub.Repository.ForumRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("topicos")
public class ForumController {

    @Autowired
    private ForumRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosForum dados, UriComponentsBuilder uriBuilder){
        if(repository.existsByTitulo(dados.titulo())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Título já cadastrado.");
        }
        if(repository.existsByMensagem(dados.mensagem())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Mensagem já cadastrada.");
        }
        var forum = new Forum(dados);
        repository.save(forum);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(forum.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhados(forum));
    }

    @GetMapping
    public ResponseEntity<Page<DadosTopicos>> listarTopicos(Pageable paginacao) {
        var page =  repository.findAll(paginacao).map(DadosTopicos::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosTopicos> listarTopicoUnico(@PathVariable Long id) {
        Optional<Forum> optionalForum = repository.findById(id);
        return optionalForum.map(forum -> ResponseEntity.ok(new DadosTopicos((forum)))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhados> atualizar(@PathVariable Long id, @RequestBody @Valid  DadosAtualizadosTopicos dados){
        Optional<Forum> optionalForum = repository.findById(id);
        if (optionalForum.isPresent()){
            var atualizarDados = optionalForum.get();
            atualizarDados.atualizar(dados);
            return ResponseEntity.ok(new DadosDetalhados(atualizarDados));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        Optional<Forum> optionalForum = repository.findById(id);
        if (optionalForum.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }
}
