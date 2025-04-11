package com.faviomunayco.modelos;

import com.google.gson.annotations.SerializedName;

public class ExchangeResponse {
    @SerializedName("result")
    String respuestaDeApi;

    @SerializedName("base_code")
    String tipoDeMonedaAConvertir;

    @SerializedName("target_code")
    String tipoDeMonedaSeleccionado;

    double montoAConvertir;

    @SerializedName("conversion_rate")
    double valorDeCambio;

    @SerializedName(value = "conversion_result",alternate = "resultadoConversion")
    double resultadoConversion;
    String marcaDeTiempo;

    public void setMontoAConvertir(double monto_a) {
        this.montoAConvertir = monto_a;
    }

    public void setMarcaDeTiempo(String currentTimestamp) {
        this.marcaDeTiempo = currentTimestamp;
    }

    @Override
    public String toString() {
        return "(" +
                "Respuesta de la Api=" + respuestaDeApi +
                ", Moneda Base=" + tipoDeMonedaAConvertir +
                ", Monto a convertir=" + montoAConvertir +
                ", Moneda Objetivo=" + tipoDeMonedaSeleccionado +
                ", Valor de Cambio=" + valorDeCambio +
                ", Cantidad Convertida=" + resultadoConversion +
                ", Marca de Tiempo=" + marcaDeTiempo +
                ')';
    }
}
