package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;

public class CalculadoraTarifasTemporadaAlta extends CalculadoraTarifas {
    protected int COSTO_POR_KM = 1000;

    @Override
    public int calcularCostoBase(Vuelo v, Cliente c) {
        if (v == null || c == null) {
            return -1;
        }

        Ruta ruta = v.getRuta();
        if (ruta == null) {
            return -1;
        }

        int distancia = Aeropuerto.calcularDistancia(ruta.getOrigen(), ruta.getDestino());
        if (distancia <= 0) {
            return -1;
        }

        return COSTO_POR_KM;
    }

    @Override
    public double calcularPorcentajeDescuento(Cliente c) {
        return 0.0;
    }
}