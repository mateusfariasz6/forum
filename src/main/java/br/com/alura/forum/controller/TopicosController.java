package br.com.alura.forum.controller;

import br.com.alura.forum.models.Curso;
import br.com.alura.forum.models.Topico;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class TopicosController {
    @RequestMapping("/topicos")
    public List<Topico> listaDeTopicos(){
        Topico topico = new Topico("Duvidas spring", "Estou com duvida sobre o spring",
                new Curso("Spring","Programação"));

        return Arrays.asList(topico,topico,topico);
    }
}
