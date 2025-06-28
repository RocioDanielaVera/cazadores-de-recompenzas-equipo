package ar.edu.unlam.pb2.agencia;

public class CazadorUrbano extends Cazador {
    public CazadorUrbano(String nombre) {
        super(nombre);
    }

    @Override
    public boolean puedeCapturar(Profugo p) {
        return this.experiencia > p.getInocencia() && !p.isNervioso();
    }

    @Override
    public void intimidar(Profugo p) {
        p.bajarInocencia(2);
        p.setNervioso(false);
    }
}
