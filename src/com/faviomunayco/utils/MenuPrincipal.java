package com.faviomunayco.utils;

import com.faviomunayco.modelos.ExchangeResponse;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {
    private CosumeApi consume = new CosumeApi();
    private boolean corriendoAplicacion = false;
    List<ExchangeResponse> listaHistoricoDeConversiones = new ArrayList<>();
    String menu = """
            **************************************************
            *       Bienvenido al conversor de monedas       *
            **************************************************
            *  1- Dólar -> Peso Argentino                    *
            *  2- Peso Argentino -> Dólar                    *
            *  3- Dólar -> Real Brasileño                    *
            *  4- Real Brasileño -> Dólar                    *
            *  5- Dólar -> Peso Colombiano                   *
            *  6- Peso Colombiano -> Dólar                   *
            *  7- Salir                                      *
            *                                                *
            *  Elija una opción válida                       *
            *                                                *
            **************************************************
            """;

    public void mostrarMenu() {
        corriendoAplicacion = true;
        while (corriendoAplicacion) {
            System.out.println(menu);
            seleccionarOpciones();
        }
    }

    public void seleccionarOpciones() {
        Scanner lectura = new Scanner(System.in);
        int opcionSeleccionada = lectura.nextInt();
        switch (opcionSeleccionada) {
            case 1:
                capturaDeInputYProcesadoDeRequest(lectura, "USD", "ARS");
                break;
            case 2:
                capturaDeInputYProcesadoDeRequest(lectura, "ARS", "USD");
                break;
            case 3:
                capturaDeInputYProcesadoDeRequest(lectura, "USD", "BRL");
                break;
            case 4:
                capturaDeInputYProcesadoDeRequest(lectura, "BRL", "USD");
                break;
            case 5:
                capturaDeInputYProcesadoDeRequest(lectura, "USD", "COP");
                break;
            case 6:
                capturaDeInputYProcesadoDeRequest(lectura, "COP", "USD");
                break;
            case 7:
                guardaArchivoJSON();
                corriendoAplicacion = false;
                break;
        }

    }

    public void capturaDeInputYProcesadoDeRequest(Scanner lectura, String de, String a) {
        double cantidadAConvertir;
        ExchangeResponse response;

        System.out.println("Ingresa la cantidad a convertir:");
        cantidadAConvertir = lectura.nextDouble();
        response = consume.ejecutaConsulta(de, a, cantidadAConvertir);

        listaHistoricoDeConversiones.add(response);

        System.out.println(response);
    }

    public void guardaArchivoJSON() {
        try {
            FileWriter escribe = new FileWriter("Historial.json");
            escribe.write(consume.convierteAJson(listaHistoricoDeConversiones));
            escribe.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
