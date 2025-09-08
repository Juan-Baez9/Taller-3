package uniandes.dpoo.aerolinea.modelo;

/**
 * Esta clase tiene la información de una ruta entre dos aeropuertos que cubre una aerolínea.
 */
public class Ruta
{
    // TODO completar
	
	// ATRIBUTOS
	
	private String codigoRuta;
	private String horaSalida;
	private String horaLlegada;
	private Aeropuerto origen;
	private Aeropuerto destino; 

	
	
	//CONSTRUCTOR

	public Ruta(String codigoRuta, String horaSalida, String horaLlegada, Aeropuerto origen, Aeropuerto destino) {
		super();
		this.codigoRuta = codigoRuta;
		this.horaSalida = horaSalida;
		this.horaLlegada = horaLlegada;
		this.origen = origen;
		this.destino = destino;
	}


	// GETTERS Y SETTERS
	public String getCodigoRuta() {
		return codigoRuta;
	}

	public Aeropuerto getOrigen() {
		return origen;
	}
	
	public Aeropuerto getDestino() {
		return destino;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public String getHoraLlegada() {
		return horaLlegada;
	}

	


	// MÉTODOS
    /**
     * Dada una cadena con una hora y minutos, retorna los minutos.
     * 
     * Por ejemplo, para la cadena '715' retorna 15.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de minutos entre 0 y 59
     */
    public static int getMinutos( String horaCompleta )
    {
        int minutos = Integer.parseInt( horaCompleta ) % 100;
        return minutos;
    }

    /**
     * Dada una cadena con una hora y minutos, retorna las horas.
     * 
     * Por ejemplo, para la cadena '715' retorna 7.
     * @param horaCompleta Una cadena con una hora, donde los minutos siempre ocupan los dos últimos caracteres
     * @return Una cantidad de horas entre 0 y 23
     */
    public static int getHoras( String horaCompleta )
    {
        int horas = Integer.parseInt( horaCompleta ) / 100;
        return horas;
    }

    public int getDuracion() {
    	int salidaHoras = getHoras(horaSalida);
        int salidaMin = getMinutos(horaSalida);
        int llegadaHoras = getHoras(horaLlegada);
        int llegadaMin = getMinutos(horaLlegada);

        int minutosSalida = salidaHoras * 60 + salidaMin;
        int minutosLlegada = llegadaHoras * 60 + llegadaMin;
        
        if (minutosLlegada < minutosSalida) {
            minutosLlegada += 24 * 60;
        }

        return minutosLlegada - minutosSalida;
    	
    }
}

