package ar.edu.unlam.pb2.agencia;

import java.util.HashSet;
import java.util.Set;

public class Zona {
    private String nombre;
    private Set<Profugo> profugos = new HashSet<>();

    public Zona(String nombre) {
        this.nombre = nombre;
    }

    public void agregarProfugo(Profugo p) {
        profugos.add(p);
    }

    public Set<Profugo> getProfugos() {
        return profugos;
    }

    public void removerProfugo(Profugo p) {
        profugos.remove(p);
    }
}
