package ar.edu.unlam.pb2.agencia;

public class CazadorSigiloso extends Cazador {

	public CazadorSigiloso(String nombre, Integer experiencia) {
		super(nombre, experiencia);
	}

	@Override
	public Boolean puedeCapturar(Profugo profugo) {
		return this.experiencia > profugo.getNivelInocencia() && profugo.getNivelHabilidad() < 50;
	}

	@Override
	public void intimidar(Profugo profugo) {
		profugo.perderNivelDeHabilidad(5);
	}
}
