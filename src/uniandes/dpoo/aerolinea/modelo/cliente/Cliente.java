package uniandes.dpoo.aerolinea.modelo.cliente;

import java.util.ArrayList;
import java.util.Collection;

import uniandes.dpoo.aerolinea.tiquetes.Tiquete;
import uniandes.dpoo.aerolinea.modelo.Vuelo;

public abstract class Cliente {
	
	// ATRIBUTOS
	protected String identificador;
    protected Collection<Tiquete> tiquetes;
    
    // GETTERS
    
	public String getIdentificador() {
		return identificador;
	}

	public Collection<Tiquete> getTiquetes() {
		return tiquetes;
	}


    // CONSTRUCTOR
    
	public Cliente(String identificador) {
        this.identificador = identificador;
        this.tiquetes = new ArrayList<>();
    }
	

	//MÁS MÉTODOS
	public void agregarTiquete(Tiquete t) {
        tiquetes.add(t);
    }

    public int calcularValorTotalTiquetes() {
        int total = 0;
        for (Tiquete t : tiquetes) {
            total += t.getTarifa();
        }
        return total;
    }

    public void usarTiquetes(Vuelo vuelo) {
        for (Tiquete t : tiquetes) {
            if (t.getVuelo().equals(vuelo)) {
                t.marcarComoUsado();
            }
        }
    }

    public abstract String getTipoCliente();

}
