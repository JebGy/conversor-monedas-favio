package com.faviomunayco.utils;

import com.faviomunayco.modelos.ExchangeResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.List;

public class CosumeApi {

    private String exchangeApi = "https://v6.exchangerate-api.com/v6/e7226bddbecb88e72fb6f58c/pair/";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    HttpClient cliente = HttpClient.newBuilder().build();

    public ExchangeResponse ejecutaConsulta(String de, String a, double cantidad) {
        String consulta = exchangeApi + de + "/" + a + "/" + cantidad;
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(consulta)).build();
        try {
            HttpResponse<String> response = cliente.send(request, HttpResponse.BodyHandlers.ofString());
            return transformaRespuesta(response.body(), cantidad);
        } catch (InterruptedException | IOException e) {
            return null;
        }
    }

    public ExchangeResponse transformaRespuesta(String body, double cantidad) {
        try {
            ExchangeResponse respuestaTransformada = gson.fromJson(body, ExchangeResponse.class);
            respuestaTransformada.setMontoAConvertir(cantidad);
            respuestaTransformada.setMarcaDeTiempo(Instant.now().toString());
            return respuestaTransformada;
        } catch (JsonSyntaxException e) {
            System.out.println("No se pudo convertir el JSON a el objeto.");
            return null;
        }
    }

    public String convierteAJson(List<ExchangeResponse> listaHistorico) {
        return gson.toJson(listaHistorico);
    }
}
