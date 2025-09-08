package uniandes.dpoo.aerolinea.persistencia;
//import java.io.IOException;

import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public interface IPersistenciaAerolinea {

	public void cargarAerolinea(String archivo, Aerolinea aerolinea); //throws IOException;

    public void salvarAerolinea(String archivo, Aerolinea aerolinea); // throws IOException;
	
}
