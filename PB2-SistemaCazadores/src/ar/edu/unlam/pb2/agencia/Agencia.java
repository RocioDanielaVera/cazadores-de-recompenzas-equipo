package ar.edu.unlam.pb2.agencia;

import java.util.*;

public class Agencia {
    private Set<Cazador> cazadores = new HashSet<>();
    private List<Profugo> profugosCapturados = new ArrayList<>();

    public void agregarCazador(Cazador c) {
        cazadores.add(c);
    }

    public void enviarCazadorAZona(Cazador cazador, Zona zona) {
        List<Profugo> capturadosEnEstaZona = new ArrayList<>();
        List<Profugo> intimidados = new ArrayList<>();

        for (Profugo p : zona.getProfugos()) {
            if (cazador.puedeCapturar(p)) {
                cazador.capturar(p);
                capturadosEnEstaZona.add(p);
                profugosCapturados.add(p);
            } else {
                cazador.intimidar(p);
                intimidados.add(p);
            }
        }

        for (Profugo p : capturadosEnEstaZona) {
            zona.removerProfugo(p);
        }
    }}
        