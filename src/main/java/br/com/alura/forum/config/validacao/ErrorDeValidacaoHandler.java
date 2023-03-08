package br.com.alura.forum.config.validacao;

import br.com.alura.forum.controller.dto.TopicoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class ErrorDeValidacaoHandler {
    @Autowired
    private MessageSource messageSource;
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<ErroDeFormularioDto> handle(MethodArgumentNotValidException exception){
        List<ErroDeFormularioDto> erroDto = new ArrayList<>();

        List<FieldError> fildErros = exception.getBindingResult().getFieldErrors();
        fildErros.forEach(e -> {
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            ErroDeFormularioDto erroDeFormularioDto = new ErroDeFormularioDto(e.getField(),mensagem);
            erroDto.add(erroDeFormularioDto);
        });
        return erroDto;
    }
}
