package ar.edu.unlam.pb2.agencia;

import java.util.Objects;

public class Profugo implements Evolucionable, Entrenable {
	
	private String nombre;
	private Integer habilidad; // 1 a 100
	private Integer inocencia; // cuanto menor, más culpable
	private Boolean nervioso;
	private Boolean proteccionLegal = false;
	private Boolean esElite = false;

	public Profugo(String nombre, Integer habilidad, Integer inocencia, Boolean nervioso) {
		this.nombre = nombre;
		this.habilidad = habilidad;
		this.inocencia = inocencia;
		this.nervioso = nervioso;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Profugo other = (Profugo) obj;
		return Objects.equals(nombre, other.nombre);
	}

	public Integer getNivelHabilidad() {
		return this.habilidad;
	}

	public Integer getNivelInocencia() {
		return this.inocencia;
	}

	public Boolean isNervioso() {
		return this.nervioso;
	}

	public void setNervioso(Boolean nervioso) {
		if (this.esElite) {
			this.nervioso = false;
		}else {
			this.nervioso = nervioso;
		}
	
	}

	@Override
	public void crecerNivelDeHabilidad(Integer nivel) {
		if (!((this.habilidad + nivel) > 100)) {
			this.habilidad = this.habilidad + nivel;
		}
	}

	@Override
	public void crecerNivelDeInocencia(Integer nivel) {
		if (!((this.inocencia + nivel) > 100)) {
			this.inocencia = this.inocencia + nivel;
		}
	}

	@Override
	public void perderNivelDeHabilidad(Integer nivel) {
		if (!((this.habilidad - nivel) < 1)) {
			this.habilidad = this.habilidad - nivel;
		}
	}

	@Override
	public void perderNivelDeInocencia(Integer nivel) {
		if (!((this.inocencia - nivel) < 1) && !this.proteccionLegal) {
			this.inocencia = this.inocencia - nivel;
		}
	}

	@Override
	public Boolean entrenarEnArtesMarciales() {
		if (!((this.habilidad * 2) > 100)) {
			this.habilidad = this.habilidad * 2;
			return true;
		}
		return false;
	}

	@Override
	public void entrenarComoElite() {
		if (this.nervioso) {
			this.nervioso = false;
		}
		this.esElite = true;
	}

	@Override
	public void entrenarConProteccionLegal() {
		if (this.inocencia < 40) {
			this.inocencia = 40;
		}
		this.proteccionLegal = true;
	}

}
