package uniandes.dpoo.aerolinea.modelo.cliente;

public class ClienteNatural extends Cliente{

	//ATRIBUTOS
	public static final String NATURAL = "Natural";
	private String nombre;
	
	// CONSTRUCTOR
	public ClienteNatural(String nombre) {
        super(nombre); // nombre como identificador
        this.nombre = nombre;
	}
	
	//MÁS MÉTODOS
    public String getIdentificador() {
    	return nombre;
    }
  

    public String getTipoCliente() {
    	return NATURAL;
    }
    

    public String getNombre() {
    	return nombre;
    }
        
	
}
