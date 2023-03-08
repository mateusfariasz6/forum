package br.com.alura.forum.controller.dto;

import br.com.alura.forum.models.Topico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class TopicoDto {
    public Long id;
    public String titulo;
    public String mensagem;
    public LocalDateTime dataCriacao;

    public TopicoDto(Topico topico){
        this.id = topico.getId();
        this.titulo = topico.getTitulo();
        this.mensagem = topico.getMensagem();
        this.dataCriacao = topico.getDataCriacao();
    }
    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public static List<TopicoDto> converter(List<Topico> topicos){
        return topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }
}
