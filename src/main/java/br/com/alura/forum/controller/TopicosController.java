package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDetalhadoRequestDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.dto.TopicoRequestSaveDto;

import br.com.alura.forum.controller.dto.TopicoRequestUpdateDto;
import br.com.alura.forum.models.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @GetMapping
    public List<TopicoDto> listaDeTopicos(String nomeCurso){
        if (nomeCurso == null){
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        }else {
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
            return TopicoDto.converter(topicos);

        }


    }
    @PostMapping
    public ResponseEntity<TopicoDto> cadastrarTopico(@RequestBody @Valid TopicoRequestSaveDto topicoRequestSaveDto, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = topicoRequestSaveDto.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new TopicoDto(topico));


    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<TopicoDetalhadoRequestDto> getTopicoPorId(@PathVariable Long id){
        Optional<Topico> topico =  topicoRepository.findById(id);
        if (topico.isPresent()){
            TopicoDetalhadoRequestDto response = new TopicoDetalhadoRequestDto(topico.get());
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.notFound().build();


    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<TopicoDto> updateTopico(@PathVariable Long id, @Valid @RequestBody TopicoRequestUpdateDto topicoRequestUpdateDto ){
        Optional<Topico> topico = topicoRepository.findById(id);

        if (topico.isPresent()){
            topico.get().setMensagem(topicoRequestUpdateDto.getMensagem());
            topico.get().setTitulo(topicoRequestUpdateDto.getTitulo());

            //Topico topicoToSave = new Topico(topico.get().getTitulo(),topico.get().getMensagem(),topico.get().getCurso());

            topicoRepository.save(topico.get());
            TopicoDto topicoDto = new TopicoDto(topico.get());
            return ResponseEntity.ok(topicoDto);
        }
        return ResponseEntity.notFound().build();
    }

@DeleteMapping(value = "/{id}")
    public ResponseEntity<?> remover(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()){

            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();


    }


}
