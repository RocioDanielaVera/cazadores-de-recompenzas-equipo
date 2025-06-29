package ar.edu.unlam.pb2.agencia;

import java.util.ArrayList;
import java.util.List;

public abstract class Cazador {
    protected String nombre;
    protected Zona zona;
    protected Integer experiencia;
    protected List<Profugo> capturados = new ArrayList<>();

    public Cazador(String nombre, Integer experiencia) {
        this.nombre = nombre;
        this.experiencia = experiencia;
    }
    
    public abstract boolean puedeCapturar(Profugo p);
    public abstract void intimidar(Profugo p);

    public void capturar(Profugo p) {
        capturados.add(p);
    }

    public List<Profugo> getCapturados() {
        return capturados;
    }

	public Boolean partirALaZonaDeCaptura(Zona zona) {
		if(zona != null) {
			this.zona = zona;
			return true;
		}
		return false;
	}

	public Zona getZonaDeCaptura() {
		return this.zona;
	}


}
