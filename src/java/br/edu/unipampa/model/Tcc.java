package br.edu.unipampa.model;
// Generated 16/07/2014 21:01:23 by Hibernate Tools 3.6.0

/**
 * Tcc generated by hbm2java
 */
public class Tcc implements java.io.Serializable {

    public static final int APROVADO = 1;
    public static final int NAO_APROVADO = 0;

    private Integer idTcc;
    private Tema tema;
    private String titulo;
    private String descricao;
    private byte[] arquivoTcc;
    private Integer status;
    private String tipoArquivo;
    private float notaOrientador;
    private float notaCoorientador;
    private float notaConvidado1;
    private float notaConvidado2;

    public Tcc() {
    }

    public Tcc(Tema tema) {
        this.tema = tema;
    }

    public Tcc(Tema tema, String titulo, String descricao, byte[] arquivoTcc, Integer status, String tipoArquivo) {
        this.tema = tema;
        this.titulo = titulo;
        this.descricao = descricao;
        this.arquivoTcc = arquivoTcc;
        this.status = status;
        this.tipoArquivo = tipoArquivo;
    }

    public Integer getIdTcc() {
        return this.idTcc;
    }

    public void setIdTcc(Integer idTcc) {
        this.idTcc = idTcc;
    }

    public Tema getTema() {
        return this.tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public byte[] getArquivoTcc() {
        return this.arquivoTcc;
    }

    public void setArquivoTcc(byte[] arquivoTcc) {
        this.arquivoTcc = arquivoTcc;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTipoArquivo() {
        return this.tipoArquivo;
    }

    public void setTipoArquivo(String tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    /**
     * @return the notaOrientador
     */
    public float getNotaOrientador() {
        return notaOrientador;
    }

    /**
     * @param notaOrientador the notaOrientador to set
     */
    public void setNotaOrientador(float notaOrientador) {
        this.notaOrientador = notaOrientador;
    }

    /**
     * @return the notaCoorientador
     */
    public float getNotaCoorientador() {
        return notaCoorientador;
    }

    /**
     * @param notaCoorientador the notaCoorientador to set
     */
    public void setNotaCoorientador(float notaCoorientador) {
        this.notaCoorientador = notaCoorientador;
    }

    /**
     * @return the notaConvidado1
     */
    public float getNotaConvidado1() {
        return notaConvidado1;
    }

    /**
     * @param notaConvidado1 the notaConvidado1 to set
     */
    public void setNotaConvidado1(float notaConvidado1) {
        this.notaConvidado1 = notaConvidado1;
    }

    /**
     * @return the notaConvidado2
     */
    public float getNotaConvidado2() {
        return notaConvidado2;
    }

    /**
     * @param notaConvidado2 the notaConvidado2 to set
     */
    public void setNotaConvidado2(float notaConvidado2) {
        this.notaConvidado2 = notaConvidado2;
    }

}
