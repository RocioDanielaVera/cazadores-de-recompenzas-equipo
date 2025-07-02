package ar.edu.unlam.pb2.agencia;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Zona {
	private String nombre;
	private Set<Profugo> profugos = new HashSet<>();

	public Zona(String nombre) {
		this.nombre = nombre;
	}

	public Integer getCantidadDeProfugos() {
		return this.profugos.size();
	}

	public Boolean agregarProfugo(Profugo profugo) {
		if (profugo == null) {
			throw new ValorNoValidoRException("El profugo no puede ser nulo.");
		}
		return this.profugos.add(profugo);
	}

	public Profugo buscarProfugo(Profugo profugo) throws ProfugoNoEncontrado {
		Profugo encontrado = null;
		for (Profugo p : this.profugos) {
			if (profugo != null && profugo.equals(p)) {
				encontrado = p;
			}
		}

		if (encontrado == null || profugo == null) {
			throw new ProfugoNoEncontrado("Profugo no encontrado.");
		} else {
			return encontrado;
		}

	}

	public ArrayList<Profugo> getProfugos() {
		return new ArrayList<>(this.profugos);
	}

	public Boolean profugoCapturado(Profugo profugo) {
		if (profugo != null && this.profugos.contains(profugo)) {
			return this.profugos.remove(profugo);
		}
		return false;
	}

}
