package br.com.alura.forum.controller.dto;

import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public class TopicoRequestUpdateDto {
    @NotEmpty @NotNull
    @Length(min = 5,max = 50)
    private String titulo;
    @NotEmpty @NotNull @Length(min = 5,max = 300)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }



}
