package com.angel.BibliotecaXpres;

import com.angel.BibliotecaXpres.modelos.DatosLibro;
import com.angel.BibliotecaXpres.modelos.RespuestaAPI;
import com.angel.BibliotecaXpres.servicios.ConexionAPI;
import com.angel.BibliotecaXpres.servicios.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

//Esta clase contiene todas funciones y acciones que realizara el programa
public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConexionAPI conexionAPI = new ConexionAPI(); //Instancia de la clase ConexionAPI
    private final String URL_BASE = "https://gutendex.com/books/?"; //Constate que guarda como valor la url de la API
    private ConvierteDatos conversor = new ConvierteDatos(); //Instancia de la clase ConvierteDatos

    //Método principal que ejecutara todas las acciones que se implementen en el programa
    public void muestraElMenu (){

        //Bloque de código para buscar los 10 libros más descargados actualmente
        System.out.println("\n***************************************************\n" +
                "TOP 10 DE LOS LIBROS MÁS DESCARGADOS ACTUALMENTE:\n");

        var json = conexionAPI.obtenerDatos(URL_BASE); //Variable que contiene la conexion del servidor de la API
        var datos = conversor.obtenerDatos(json, RespuestaAPI.class); //Variable que convierte el Json recibido a objetos de la clase RespuestaAPI
        //System.out.println(datos);

        //Creación de la lista que capturara solo los primeros 10 registros de la API para mostrarlos en el formato del top 10
        //En este fragmento de código no utilizamos el parametro "download_count" porque la API ya envía de forma descendente del más descargado al menos descargado
        List<DatosLibro> top10MasDescargados = datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibro::totalDeDescargas).reversed())
                .limit(10)
                .collect(Collectors.toList());

        //La siguiente lista muestra en pantalla los resultados de la lista anterior
        top10MasDescargados.forEach(l -> System.out.println("--> " + l.titulo().toUpperCase()));
        System.out.println("***************************************************\n");
        //Fin de bloque de código para buscar los libros más descargados

        //Bloque de código para buscar estadísticas de los libros por su total de descargas
        System.out.println("***************************************************\n" +
                "ESTADISTICAS DE DESCARGAS DE LOS PRIMEROS 32 LIBROS REGISTRADOS EN LA API DE GUTENDEX");

        DoubleSummaryStatistics est = datos.resultados().stream()
                .filter(l -> l.totalDeDescargas() > 0.0)
                .limit(32)
                .collect(Collectors.summarizingDouble(DatosLibro::totalDeDescargas));

        System.out.println("\nMedia de descargas: " + est.getAverage() +
                "\nNúmero mayor de descargas: " + est.getMax() +
                "\nNúmero menor de descargas: " + est.getMin() +
                "\nTotal de libros evaluados: " + est.getCount());
        System.out.println("***************************************************\n");
        //Fin de bloque de código para buscar estadísticas de los libros

        //Bloque de código para buscar un libro mediante un fragmento del título
        System.out.println("\nIngrese el nombre del libro que desea buscar:");

        var fragmentoDeTitulo = teclado.nextLine(); //Le agregamos el fragmento De Título que ingreso el usuario a la variable
        json = conexionAPI.obtenerDatos(URL_BASE +  "languages=es,en&search=" + fragmentoDeTitulo.replace(" ", "+"));
        datos = conversor.obtenerDatos(json, RespuestaAPI.class);

        //Objeto del tipo Optional para buscar en la API si el título buscado está presente o no en ella
        Optional<DatosLibro> libroBuscado = datos.resultados().stream()
                .filter(e -> e.titulo().toUpperCase()
                        .contains(fragmentoDeTitulo.toUpperCase()))
                .findFirst();

        //Bloque if para mostrar al usuario el resultado de su búsqueda
        if (libroBuscado.isPresent()) {
            System.out.println("Libro encontrado!!");

            //Le asignamos por separado el objeto del Json que necesitamos a 3 variables para mostrar de manera más legible el resultado
            String titulo = libroBuscado.get().titulo();
            String autor = libroBuscado.get().autores().get(0).autor();
            Integer totalDescargas = libroBuscado.get().totalDeDescargas();

            System.out.println("Título: " + titulo +
                "\nAutor: " + autor +
                "\nNúmero de descargas: " + totalDescargas);
        }else {
            System.out.println("Libro mo encontrado!!");
        }
        //Fin de bloque de código de búsqueda por fragmento de título


    }
}
