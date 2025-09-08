package uniandes.dpoo.aerolinea.consola;


import uniandes.dpoo.aerolinea.exceptions.VueloSobrevendidoException;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;
import uniandes.dpoo.aerolinea.persistencia.CentralPersistencia;

public class ConsolaArerolinea extends ConsolaBasica
{
    private Aerolinea unaAerolinea;

    public void correrAplicacion()
    {
        unaAerolinea = new Aerolinea();

		boolean continuar = true;
		while (continuar) {
		    String[] opciones = {
		        "Cargar/Salvar Aerolínea (JSON)",   //  Req 1
		        "Programar vuelo",                  //  Req 2
		        "Vender tiquetes",                  //  Req 3
		        "Registrar vuelo realizado",        //  Req 4
		        "Consultar saldo pendiente",        //  Req 5
		        "Salir"
		    };

		    int opcion = mostrarMenu("Menú Principal", opciones);

		    switch (opcion) {
		        case 1:
		            String archivo = pedirCadenaAlUsuario("Digite el nombre del archivo JSON (ej: aerolineas.json)");
		            try {
		                unaAerolinea.cargarAerolinea("./datos/" + archivo, CentralPersistencia.JSON);
		                System.out.println("Aerolínea cargada correctamente desde " + archivo);

		                // Guardar de ejemplo
		                if (pedirConfirmacionAlUsuario("¿Desea salvar nuevamente la aerolínea?")) {
		                    unaAerolinea.salvarAerolinea("./datos/" + archivo, CentralPersistencia.JSON);
		                    System.out.println("Aerolínea guardada en " + archivo);
		                }
		            } catch (Exception e) {
		                System.out.println("Error cargando/salvando: " + e.getMessage());
		            }
		            break;

		        case 2:
		            String fecha = pedirCadenaAlUsuario("Digite la fecha del vuelo (YYYY-MM-DD)");
		            String codigoRuta = pedirCadenaAlUsuario("Digite el código de la ruta");
		            String avion = pedirCadenaAlUsuario("Digite el nombre del avión");
		            try {
		                unaAerolinea.programarVuelo(fecha, codigoRuta, avion);
		                System.out.println("Vuelo programado en " + fecha + " para la ruta " + codigoRuta);
		            } catch (Exception e) {
		                System.out.println("Error programando vuelo: " + e.getMessage());
		            }
		            break;


		        case 3: // Vender tiquetes
		            String cliente = pedirCadenaAlUsuario("Digite el identificador del cliente");
		            String fecha1 = pedirCadenaAlUsuario("Digite la fecha del vuelo (YYYY-MM-DD)");
		            String ruta = pedirCadenaAlUsuario("Digite el código de la ruta");
		            int cantidad = pedirEnteroAlUsuario("Digite la cantidad de tiquetes");

		            try {
		                int total = unaAerolinea.venderTiquetes(cliente, fecha1, ruta, cantidad);
		                System.out.println("Listo! " + cantidad + " tiquetes vendidos a " + cliente + " en la ruta " + ruta);
		                System.out.println("💰 Valor total: $" + total);
		            } catch (VueloSobrevendidoException e) {
		                System.out.println("No se pudieron vender los tiquetes: vuelo sobrevendido.");
		            } catch (Exception e) {
		                System.out.println("Error vendiendo tiquetes: " + e.getMessage());
		            }
		            break;

		        case 4:
		            String vueloRealizado = pedirCadenaAlUsuario("Digite el código de la ruta del vuelo realizado");
		            String fechaVuelo = pedirCadenaAlUsuario("Digite la fecha del vuelo realizado");
		            try {
		                unaAerolinea.registrarVueloRealizado(vueloRealizado, fechaVuelo);
		                System.out.println("Vuelo " + vueloRealizado + " en " + fechaVuelo + " marcado como realizado");
		            } catch (Exception e) {
		                System.out.println("Error registrando vuelo realizado: " + e.getMessage());
		            }
		            break;

		        case 5: // Consultar saldo pendiente
		            String clienteSaldo = pedirCadenaAlUsuario("Digite el identificador del cliente");
		            try {
		                String saldo = unaAerolinea.consultarSaldoPendienteCliente(clienteSaldo);
		                System.out.println("El saldo pendiente del cliente " + clienteSaldo + " es: $" + saldo);
		            } catch (Exception e) {
		                System.out.println("Error consultando saldo: " + e.getMessage());
		            }
		            break;


		        case 6:
		            continuar = false;
		            System.out.println("Saliendo del sistema...");
		            break;
		    }
		}
    }

    public static void main(String[] args) {
        ConsolaArerolinea ca = new ConsolaArerolinea();
        ca.correrAplicacion();
    }
}
