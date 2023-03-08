package br.com.alura.forum.controller.dto;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.CursoRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class TopicoRequestSaveDto {
     @NotEmpty @NotNull @Length(min = 5,max = 50)
    public String titulo;
    @NotEmpty @NotNull @Length(min = 5,max = 300)
    public String mensagem;
    @NotEmpty @NotNull @Length(min = 3,max = 50)
    public String nomeCurso;

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

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Topico converter(CursoRepository cursoRepository) {
        Curso curso = cursoRepository.findByNome(nomeCurso);
        return new Topico(titulo,mensagem,curso);
    }
}
