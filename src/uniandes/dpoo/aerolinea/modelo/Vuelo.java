package uniandes.dpoo.aerolinea.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uniandes.dpoo.aerolinea.modelo.cliente.Cliente;
import uniandes.dpoo.aerolinea.modelo.tarifas.CalculadoraTarifas;
import uniandes.dpoo.aerolinea.tiquetes.GeneradorTiquetes;
import uniandes.dpoo.aerolinea.tiquetes.Tiquete;

public class Vuelo {
    private String fecha;
    private Ruta ruta;
    private Avion avion;
    private List<Tiquete> tiquetes; 

    public Vuelo(Ruta ruta, String fecha, Avion avion) {
        this.ruta = ruta;
        this.fecha = fecha;
        this.avion = avion;
        this.tiquetes = new ArrayList<>(); 
    }

    public Ruta getRuta() {
        return ruta;
    }

    public String getFecha() {
        return fecha;
    }

    public Avion getAvion() {
        return avion;
    }

    public Collection<Tiquete> getTiquetes() {
        return tiquetes; 
    }

    public int venderTiquetes(Cliente cliente, CalculadoraTarifas calculadora, int cantidad) {
        int r = 0;

        for (int i = 0; i < cantidad; i++) {
            Tiquete tiquete = GeneradorTiquetes.generarTiquete(
                this, cliente,
                (int) calculadora.calcularTarifa(this, cliente)
            );

            GeneradorTiquetes.registrarTiquete(tiquete);
            cliente.agregarTiquete(tiquete);
            tiquetes.add(tiquete); 
            r++;
        }
        return (r > 0) ? r : -1;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vuelo) {
            Vuelo otro = (Vuelo) obj;
            return this.getRuta().equals(otro.getRuta()) && this.getFecha().equals(otro.getFecha());
        }
        return false;
    }
}
