package ar.edu.unlam.pb2.agencia;

import java.util.ArrayList;
import java.util.List;

public abstract class Cazador {
    protected String nombre;
    protected Integer experiencia;
    protected List<Profugo> capturados = new ArrayList<>();

    public Cazador(String nombre) {
        this.nombre = nombre;
        this.experiencia = 0;
    }

    public abstract boolean puedeCapturar(Profugo p);
    public abstract void intimidar(Profugo p);

    public void capturar(Profugo p) {
        capturados.add(p);
    }

    public List<Profugo> getCapturados() {
        return capturados;
    }


}
