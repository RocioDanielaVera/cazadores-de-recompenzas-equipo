package ar.edu.unlam.pb2.agencia;

import java.util.*;

public class Agencia {
	private String nombreDeLaAgencia;
	private Map<Integer, Cazador> cazadoresRegistrados = new HashMap<>();

	public Agencia(String nombreDeLaAgencia) {
		this.nombreDeLaAgencia = nombreDeLaAgencia;
	}

	public void enviarCazadorAZona(Cazador cazador, Zona zona) {
		cazador.partirALaZonaDeCaptura(zona);
	}

	public Cazador buscarCazador(Integer numeroDeLicencia) throws NroDeLicenciaNoRegistradaException {
		Cazador encontrado = this.cazadoresRegistrados.getOrDefault(numeroDeLicencia, null);
		if (encontrado == null) {
			throw new NroDeLicenciaNoRegistradaException("El numero de licencia no existe en los registros de la Agencia");
		} else {
			return encontrado;
		}
	}

	public void registrarAUnCazador(Integer numeroDeLicencia, Cazador nuevo) {
		cazadoresRegistrados.put(numeroDeLicencia, nuevo);
	}

	public Boolean enviarCazadorAUnaZona(Zona zona, Integer numeroDeLicencia)throws NroDeLicenciaNoRegistradaException {
		Cazador cazadorElegido = buscarCazador(numeroDeLicencia);
		if (zona != null) {
			cazadorElegido.partirALaZonaDeCaptura(zona);
			return true;
		}
		return false;
	}

}
