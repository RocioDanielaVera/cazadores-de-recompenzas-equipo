package ar.edu.unlam.pb2.agencia;

import java.time.LocalDateTime;

public class Reporte {
    private Cazador cazador;
    private Profugo profugo;
    private Zona zona;
    private LocalDateTime fechaCaptura;

    public Reporte(Cazador cazador, Profugo profugo, Zona zona) {
        this.cazador = cazador;
        this.profugo = profugo;
        this.zona = zona;
        this.fechaCaptura = LocalDateTime.now();
    }

    public Cazador getCazador() {
        return cazador;
    }

    public Profugo getProfugo() {
        return profugo;
    }

    public Zona getZona() {
        return zona;
    }

    public LocalDateTime getFechaCaptura() {
        return fechaCaptura;
    }
}
