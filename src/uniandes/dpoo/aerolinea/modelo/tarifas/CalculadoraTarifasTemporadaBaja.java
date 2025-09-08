package uniandes.dpoo.aerolinea.modelo.tarifas;

import uniandes.dpoo.aerolinea.modelo.Vuelo;
import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteCorporativo;
import uniandes.dpoo.aerolinea.modelo.cliente.ClienteNatural;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;;;

public class CalculadoraTarifasTemporadaBaja extends CalculadoraTarifas {
    protected int COSTO_POR_KM_NATURAL = 600;
    protected int COSTO_POR_KM_COPORATIVO = 900;
    protected double DESCUENTO_PEQ = 0.02;
    protected double DESCUENTO_MEDIANAS = 0.1;
    protected double DESCUENTO_GRANDES = 0.2;

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

        if (distancia <= 1000) {
            return COSTO_POR_KM_NATURAL;
        } else if (distancia <= 3000) {
            return COSTO_POR_KM_COPORATIVO;
        } else {
            return -1;
        }
    }

    @Override
    public double calcularPorcentajeDescuento(Cliente c) {
        if (c == null) {
            return -1.0;
        }

        if (c.getTipoCliente().equals(ClienteNatural.NATURAL)) {
            return DESCUENTO_PEQ;
        } else if (c.getTipoCliente().equals(ClienteCorporativo.CORPORATIVO)) {
            return DESCUENTO_MEDIANAS;
        } else {
            return DESCUENTO_GRANDES;
        }
    }
}
