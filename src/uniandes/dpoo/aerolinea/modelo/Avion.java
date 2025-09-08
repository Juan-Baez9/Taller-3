package uniandes.dpoo.aerolinea.modelo;

public class Avion {

	// Atributos
	
	private String nombre;
	private int capacidad;
	
	// GETTERS
	
	public String getNombre() {
		return nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	// CONSTRUCTOR
	
	public Avion(String nombre, int capacidad) {
		//super();
		this.nombre = nombre;
		this.capacidad = capacidad;
	}
	
}
