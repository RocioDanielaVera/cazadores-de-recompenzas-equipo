package ar.edu.unlam.pb2.agencia;

public interface Evolucionable {
	void crecerNivelDeInocencia(Integer nivel);

	void perderNivelDeInocencia(Integer nivel);

	void crecerNivelDeHabilidad(Integer nivel);

	void perderNivelDeHabilidad(Integer nivel);
}
