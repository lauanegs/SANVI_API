package com.sanvi.sanvi_api.controller.dto;

public class AlertaDTO {
    private Long id;
    private String titulo;
    private String conteudo;

    public AlertaDTO(Long id, String titulo, String conteudo) {
        this.id = id;
        this.titulo = titulo;
        this.conteudo = conteudo;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getConteudo() { return conteudo; }
}
