package ar.edu.unlam.pb2.agencia;

import java.util.*;

public abstract class Cazador {
    protected String nombre;
    protected Integer experiencia;
    protected Zona zonaActual;

    public Cazador(String nombre) {
        this.nombre = nombre;
        this.experiencia = new Random().nextInt(100) + 1; // 1 a 100
    }

    public void partirALaZonaDeCaptura(Zona zona) {
        this.zonaActual = zona;
    }

    public boolean buscarProfugo() {
        if (zonaActual == null) return false;

        List<Profugo> profugos = new ArrayList<>(zonaActual.getProfugos());
        List<Profugo> capturados = new ArrayList<>();
        List<Profugo> intimidados = new ArrayList<>();

        for (Profugo p : profugos) {
            if (this.puedeCapturar(p)) {
                capturados.add(p);
            } else {
                this.intimidarProfugo(p);
                intimidados.add(p);
            }
        }

        for (Profugo p : capturados) {
            zonaActual.removerProfugo(p);
        }

        this.incrementarExperiencia(intimidados, capturados.size());

        return !capturados.isEmpty();
    }

    public void intimidarProfugo(Profugo profugo) {
        profugo.restarInocencia(2);
        this.intimidar(profugo);
    }

    public void incrementarExperiencia(List<Profugo> intimidados, int cantidadCapturados) {
        int minHabilidad = intimidados.stream()
            .mapToInt(Profugo::getNivelHabilidad)
            .min()
            .orElse(0);

        int experienciaGanada = minHabilidad + (2 * cantidadCapturados);
        this.experiencia += experienciaGanada;

        if (this.experiencia > 100) {
            this.experiencia = 100;
        }
    }

    public Integer getExperiencia() {
        return experiencia;
    }

    public String getNombre() {
        return nombre;
    }
    

    public abstract boolean puedeCapturar(Profugo profugo);
    public abstract void intimidar(Profugo profugo);
}
