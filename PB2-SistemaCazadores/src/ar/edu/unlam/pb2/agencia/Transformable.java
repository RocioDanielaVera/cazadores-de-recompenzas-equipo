package ar.edu.unlam.pb2.agencia;

public interface Transformable {
	void sumarInocencia(Integer nivel);

	void restarInocencia(Integer nivel);

	void restarHabilidad(Integer nivel);

	void sumarHabilidad(Integer nivel);
}
