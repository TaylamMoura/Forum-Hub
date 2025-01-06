package com.alura.forum_hub.Controller;

import com.alura.forum_hub.Forum.DadosForum;
import com.alura.forum_hub.Forum.Forum;
import com.alura.forum_hub.Repository.ForumRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("topicos")
public class ForumController {

    @Autowired
    private ForumRepository repository;

    @PostMapping
    @Transactional
    public Forum registrarTopico(@RequestBody @Valid DadosForum dados){
        Forum forum = new Forum(dados);
        return repository.save(forum);
    }

}
