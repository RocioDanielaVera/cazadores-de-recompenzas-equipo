package ar.edu.unlam.pb2.agencia;

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
		if (profugo != null) {
			return this.profugos.add(profugo);
		}
		return false;
	}

	public Profugo buscarProfugo(Profugo profugo) {
		for (Profugo encontrado : this.profugos) {
			if (profugo.equals(encontrado)) {
				return encontrado;
			}
		}

		return null;
	}

	public Set<Profugo> getProfugos() {
		return this.profugos;
	}

	public Boolean profugoCapturado(Profugo profugo) {
		if (profugo != null && this.profugos.contains(profugo)) {
			return this.profugos.remove(profugo);
		}
		return false;
	}

}
