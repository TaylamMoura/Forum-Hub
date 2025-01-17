package com.alura.forum_hub.Controller;

import com.alura.forum_hub.DTO.DadosAtualizadosTopicos;
import com.alura.forum_hub.DTO.DadosDetalhados;
import com.alura.forum_hub.DTO.DadosForum;
import com.alura.forum_hub.DTO.DadosTopicosGeral;
import com.alura.forum_hub.Domain.Forum.Forum;
import com.alura.forum_hub.Repository.ForumRepository;
import com.alura.forum_hub.Service.TopicosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private TopicosService service;


    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosForum dados, UriComponentsBuilder uriBuilder){
        var forum = service.cadastrarTopico(dados);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(forum.getId()).toUri();
        return  ResponseEntity.created(uri).body(new DadosDetalhados(forum));
    }

    @GetMapping
    public ResponseEntity<Page<DadosTopicosGeral>> listarTopicos(Pageable paginacao) {
        var page =  repository.findAll(paginacao).map(DadosTopicosGeral::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhados> listarTopicoUnico(@PathVariable Long id) {
        Optional<Forum> optionalForum = repository.findById(id);
        return optionalForum.map(forum -> ResponseEntity.ok(new DadosDetalhados((forum)))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhados> atualizar(@PathVariable Long id, @RequestBody @Valid DadosAtualizadosTopicos dados){
        var forum = service.atualizarTopico(id, dados);
        return ResponseEntity.ok(new DadosDetalhados(forum));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(@PathVariable Long id){
        service.deletarTopico(id);
        return ResponseEntity.noContent().build();
        }
    }

