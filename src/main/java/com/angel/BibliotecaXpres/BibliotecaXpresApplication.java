package com.angel.BibliotecaXpres;

import com.angel.BibliotecaXpres.modelos.DatosLibro;
import com.angel.BibliotecaXpres.modelos.RespuestaAPI;
import com.angel.BibliotecaXpres.servicios.ConexionAPI;
import com.angel.BibliotecaXpres.servicios.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BibliotecaXpresApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaXpresApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {

        Principal principal = new Principal();
        principal.muestraElMenu();

        //Bloque de líneas para validar que funcionara el método obtenerDatos de la clase ConexionAPI
//        ConexionAPI conexionAPI = new ConexionAPI();
//        ConvierteDatos conversor = new ConvierteDatos();
//
//        var json = conexionAPI.obtenerDatos("https://gutendex.com/books/?search=divine");
//        RespuestaAPI respuesta = conversor.obtenerDatos(json, RespuestaAPI.class);
//        DatosLibro libro = respuesta.resultados().get(0);
//        String titulo = libro.titulo();
//        String autor = libro.autores().get(0).autor();
//        Integer descargas = libro.totalDeDescargas();
//
//        System.out.println(json);
//        System.out.println(titulo + autor + descargas);
    }
}
