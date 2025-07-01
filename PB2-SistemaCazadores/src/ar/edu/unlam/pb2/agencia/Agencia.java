package ar.edu.unlam.pb2.agencia;

import java.util.*;

public class Agencia {
	private List<Reporte> reportes = new ArrayList<>();
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
			throw new NroDeLicenciaNoRegistradaException(
					"El numero de licencia no existe en los registros de la Agencia");
		} else {
			return encontrado;
		}
	}

	public void registrarAUnCazador(Integer numeroDeLicencia, Cazador nuevo) {
		cazadoresRegistrados.put(numeroDeLicencia, nuevo);
		nuevo.registrarseEnUnaAgencia(this);
	}

	public Boolean enviarCazadorAUnaZona(Zona zona, Integer numeroDeLicencia)
			throws NroDeLicenciaNoRegistradaException {
		Cazador cazadorElegido = buscarCazador(numeroDeLicencia);
		if (zona != null) {
			cazadorElegido.partirALaZonaDeCaptura(zona);
			return true;
		}
		return false;
	}

	public void enviarReporteALaLista(Reporte reporte) {
		reportes.add(reporte);
	}

	public List<Profugo> getProfugosCapturados() {
		List<Profugo> resultado = new ArrayList<>();
		for (Reporte r : reportes) {
			resultado.add(r.getProfugo());
		}
		return resultado;

	}

	public Profugo getProfugoMasHabilCapturado() {
		return reportes.stream().map(Reporte::getProfugo).max(Comparator.comparingInt(Profugo::getNivelHabilidad))
				.orElse(null);
	}

	public Cazador getCazadorConMasCapturas() {
		Map<Cazador, Integer> contador = getContadorDeCapturasPorCazador();
		Cazador conMas = null;
		int max = 0;

		for (Map.Entry<Cazador, Integer> entrada : contador.entrySet()) {
			if (entrada.getValue() > max) {
				conMas = entrada.getKey();
				max = entrada.getValue();
			}
		}

		return conMas;
	}

	public Map<Cazador, Integer> getContadorDeCapturasPorCazador() {
		Map<Cazador, Integer> contador = new HashMap<>();

		for (Reporte r : reportes) {
			Cazador c = r.getCazador();
			contador.put(c, contador.getOrDefault(c, 0) + 1);
		}

		return contador;
	}

}
