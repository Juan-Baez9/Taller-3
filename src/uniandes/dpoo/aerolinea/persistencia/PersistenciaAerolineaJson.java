package uniandes.dpoo.aerolinea.persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import uniandes.dpoo.aerolinea.exceptions.AeropuertoDuplicadoException;
import uniandes.dpoo.aerolinea.modelo.Aeropuerto;
import uniandes.dpoo.aerolinea.modelo.Avion;
import uniandes.dpoo.aerolinea.modelo.Ruta;
import uniandes.dpoo.aerolinea.modelo.Aerolinea;

public class PersistenciaAerolineaJson implements IPersistenciaAerolinea {

    @Override
    public void cargarAerolinea(String archivo, Aerolinea aerolinea) {
        try {
            String jsonCompleto = new String(Files.readAllBytes(new File(archivo).toPath()));
            JSONObject raiz = new JSONObject(jsonCompleto);

            // 1) AVIONES (espera "nombre" y "capacidad")
            if (raiz.has("aviones")) {
                JSONArray jAviones = raiz.getJSONArray("aviones");
                for (int i = 0; i < jAviones.length(); i++) {
                    JSONObject jAv = jAviones.getJSONObject(i);
                    String nombre = jAv.has("nombre") ? jAv.getString("nombre") : jAv.optString("codigo", "Avion" + i);
                    int capacidad = jAv.has("capacidad") ? jAv.getInt("capacidad") : jAv.optInt("capacidad", 100);
                    aerolinea.agregarAvion(new Avion(nombre, capacidad));
                }
            }

            // 2) AEROPUERTOS (espera "codigo","nombre","latitud","longitud","ciudad")
            Map<String, Aeropuerto> mapaAeropuertos = new HashMap<>();
            if (raiz.has("aeropuertos")) {
                JSONArray jAero = raiz.getJSONArray("aeropuertos");
                for (int i = 0; i < jAero.length(); i++) {
                    JSONObject j = jAero.getJSONObject(i);
                    String codigo = j.optString("codigo", j.optString("codigoAero", "X" + i));
                    String nombre = j.optString("nombre", "SinNombre");
                    double lat = j.has("latitud") ? j.getDouble("latitud") : j.optDouble("lat", 0.0);
                    double lon = j.has("longitud") ? j.getDouble("longitud") : j.optDouble("lon", 0.0);
                    String ciudad = j.optString("ciudad", "Desconocida");
                    Aeropuerto a = new Aeropuerto(codigo, nombre, lat, lon, ciudad);
                    mapaAeropuertos.put(codigo, a);
                }
            }

            // 3) RUTAS (espera "codigoRuta","horaSalida","horaLlegada","origen","destino")
            if (raiz.has("rutas")) {
                JSONArray jRutas = raiz.getJSONArray("rutas");
                for (int i = 0; i < jRutas.length(); i++) {
                    JSONObject jr = jRutas.getJSONObject(i);
                    String codigoRuta = jr.getString("codigoRuta");
                    String horaSalida = jr.optString("horaSalida", "0000");
                    String horaLlegada = jr.optString("horaLlegada", "0000");
                    Aeropuerto origen = mapaAeropuertos.get(jr.getString("origen"));
                    Aeropuerto destino = mapaAeropuertos.get(jr.getString("destino"));
                    // si origen o destino son null, podrías lanzar excepción o registrar con null
                    aerolinea.agregarRuta(new Ruta(codigoRuta, horaSalida, horaLlegada, origen, destino));
                }
            }

            // 4) VUELOS (espera "fecha","codigoRuta","avion")
            if (raiz.has("vuelos")) {
                JSONArray jVuelos = raiz.getJSONArray("vuelos");
                for (int i = 0; i < jVuelos.length(); i++) {
                    JSONObject jv = jVuelos.getJSONObject(i);
                    String fecha = jv.getString("fecha");
                    String codigoRuta = jv.has("codigoRuta") ? jv.getString("codigoRuta") : jv.optString("ruta", null);
                    String nombreAvion = jv.has("avion") ? jv.getString("avion") : jv.optString("nombreAvion", null);
                    try {
                        aerolinea.programarVuelo(fecha, codigoRuta, nombreAvion);
                    } catch (Exception e) {
                        // si algo falla, lo informamos (o lo podés propagar)
                        System.err.println("No se pudo crear vuelo: " + e.getMessage());
                    }
                }
            }

        } catch (IOException | AeropuertoDuplicadoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void salvarAerolinea(String archivo, Aerolinea aerolinea) {
        
    }
}