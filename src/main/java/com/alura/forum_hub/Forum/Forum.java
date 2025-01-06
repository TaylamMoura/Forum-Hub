package com.alura.forum_hub.Forum;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@Table(name = "topicos")
public class Forum {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;

    @Column(name = "data_criacao")
    private Timestamp dataCriacao;

    private String status = "nao_respondido";
    private String autor;
    private String curso;

    public Forum(DadosForum dados) {
        this.mensagem = dados.mensagem();
        this.titulo = dados.titulo();
        this.dataCriacao = new Timestamp(System.currentTimeMillis());
        this.status = "nao_respondido";
        this.autor = dados.autor();
        this.curso = dados.curso();
    }

    public void alterarStatus(){
        this.status = "respondido";
    }
}
