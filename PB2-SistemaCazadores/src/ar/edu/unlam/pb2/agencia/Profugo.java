package ar.edu.unlam.pb2.agencia;

import java.util.Objects;

public class Profugo implements Evolucionable, Entrenable {

	private String nombre;
	private Integer habilidad = 1;
	private Integer inocencia = 1;
	private Boolean nervioso;
	private Boolean proteccionLegal = false;
	private Boolean esElite = false;

	public Profugo(String nombre, Integer habilidad, Integer inocencia, Boolean nervioso) {
		this.nombre = nombre;
		setInocencia(inocencia);
		setHabilidad(habilidad);
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
		} else {
			this.nervioso = nervioso;
		}

	}

	public Boolean valorNoValido(Integer nivel) {
		return nivel <= 0 || nivel == null;
	}

	private void setInocencia(Integer inocencia) {
		if (valorNoValido(inocencia)) {
			throw new ValorNoValidoRException(
					"El valor ingresado no es valido. Debe ingresar un numero entero mayor o igual a 1");
		}

		if (inocencia > 100) {
			this.inocencia = 100;
		} else if (inocencia > 1 && inocencia <= 100) {
			this.inocencia = inocencia;
		}
	}

	private void setHabilidad(Integer nivel) {
		if (valorNoValido(nivel)) {
			throw new ValorNoValidoRException(
					"El valor ingresado no es valido. Debe ingresar un numero entero mayor o igual a 1");
		}

		if (nivel > 100) {
			this.habilidad = 100;
		} else if ((nivel > 0) && (nivel <= 100)) {
			this.habilidad = nivel;
		}

	}

	@Override
	public void perderNivelDeHabilidad(Integer nivel) {
		if (valorNoValido(nivel)) {
			throw new ValorNoValidoRException(
					"El valor ingresado no es valido. Debe ingresar un numero entero mayor o igual a 1");
		}

		if (!((this.habilidad - nivel) < 1)) {
			this.habilidad = this.habilidad - nivel;
		} else if (((this.habilidad - nivel) < 1)) {
			this.habilidad = 1;
		}
	}

	@Override
	public void perderNivelDeInocencia(Integer nivel) {
		if (valorNoValido(nivel)) {
			throw new ValorNoValidoRException(
					"El valor ingresado no es valido. Debe ingresar un numero entero mayor o igual a 1");
		}

		if (!((this.inocencia - nivel) < 1) && !this.proteccionLegal) {
			this.inocencia = this.inocencia - nivel;
		} else if ((this.inocencia - nivel) < 1 && !this.proteccionLegal) {
			this.inocencia = 1;
		}
	}

	@Override
	public void entrenarEnArtesMarciales() {
		if (!((this.habilidad * 2) > 100)) {
			this.habilidad = this.habilidad * 2;
		} else if ((this.habilidad * 2) > 100) {
			this.habilidad = 100;
		}
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
