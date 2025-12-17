package com.angel.BibliotecaXpres.servicios;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
//Esta clase se encarga de realizar la solicitud a la API
public class ConexionAPI {

    //Método de la clase para realizar la request del cliente recibiendo como parámetro la url de la API y recibir el
    //response del servidor mediante los métodos que proporciona la clase HTTP
    public String obtenerDatos (String url){

        HttpClient client = HttpClient.newHttpClient(); //Variable del tipo HttpClient para el cliente
        HttpRequest request = HttpRequest.newBuilder() //Variable del tipo HttpRequest para realizar la consulta que realiza el cliente
                .uri(URI.create(url))
                .build();
        HttpResponse<String> response = null; //Variable del tipo HttpResponse para capturar la respuesta del servidor de la API

        //Bloque try-catch para capturar cualquier error al recibir el response del servidor de la API
        try {
            response = client //Se le atribuye a la variable response la variable client con la request de la API
                    .send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        String json = response.body(); //Variable para capturar el response que devuelve el servidor de la API para retornarla al final del método
        return json;
    }
}
