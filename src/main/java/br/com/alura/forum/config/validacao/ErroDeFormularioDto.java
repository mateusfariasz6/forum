package br.com.alura.forum.config.validacao;

public class ErroDeFormularioDto {
    public String campo;
    public String mensagemError;

    public ErroDeFormularioDto(String campo, String mensagemError){
        this.campo = campo;
        this.mensagemError = mensagemError;

    }

    public String getCampo() {
        return campo;
    }

    public String getMensagemError() {
        return mensagemError;
    }
}
