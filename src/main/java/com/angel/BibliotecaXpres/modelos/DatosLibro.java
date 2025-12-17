package com.angel.BibliotecaXpres.modelos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(@JsonAlias("title") String titulo,
                         @JsonAlias("authors")List<DatosAutor> autores,
                         @JsonAlias("download_count") Integer totalDeDescargas) {
    @Override
    public String toString() {
        return "Título: '" + titulo +
                ", Autor: " + autores.get(0).autor() +
                ", TotalDeDescargas: " + totalDeDescargas;
    }
}
