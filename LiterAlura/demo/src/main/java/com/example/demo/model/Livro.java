package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    private String idioma;

    private Double numeroDownloads;

    // Construtores
    public Livro() {}

    public Livro(String titulo, Autor autor, String idioma, Double numeroDownloads) {
        this.titulo = titulo;
        this.autor = autor;
        this.idioma = idioma;
        this.numeroDownloads = numeroDownloads;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public Autor getAutor() { return autor; }
    public void setAutor(Autor autor) { this.autor = autor; }

    public String getIdioma() { return idioma; }
    public void setIdioma(String idioma) { this.idioma = idioma; }

    public Double getNumeroDownloads() { return numeroDownloads; }
    public void setNumeroDownloads(Double numeroDownloads) { this.numeroDownloads = numeroDownloads; }

    @Override
    public String toString() {
        return String.format("""
                ----- LIVRO -----
                Título: %s
                Autor: %s
                Idioma: %s
                Downloads: %.0f
                ----------------
                """,
                titulo,
                autor != null ? autor.getNome() : "Desconhecido",
                idioma,
                numeroDownloads != null ? numeroDownloads : 0.0);
    }
}