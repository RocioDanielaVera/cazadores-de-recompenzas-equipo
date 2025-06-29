package ar.edu.unlam.pb2.agencia;

public class CazadorRural extends Cazador {

	public CazadorRural(String nombre, Integer experiencia) {
		super(nombre, experiencia);
	}

	@Override
	public boolean puedeCapturar(Profugo p) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void intimidar(Profugo p) {
		// TODO Auto-generated method stub
		
	}

}
