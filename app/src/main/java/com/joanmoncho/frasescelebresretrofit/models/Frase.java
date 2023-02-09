package com.joanmoncho.frasescelebresretrofit.models;

public class Frase {
    private int id;
    private String texto;
    private String fechaProgramada;
    private Autor autor;
    private Categoria categoria;

    public Frase(String texto, String fechaProgramada, Autor autor, Categoria categoria) {
        this.texto = texto;
        this.fechaProgramada = fechaProgramada;
        this.autor = autor;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Frase{" +
                "id=" + id +
                ", texto='" + texto + '\'' +
                ", fechaProgramada='" + fechaProgramada + '\'' +
                ", autor=" + autor +
                ", categoria=" + categoria +
                '}';
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public String getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(String fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }


}