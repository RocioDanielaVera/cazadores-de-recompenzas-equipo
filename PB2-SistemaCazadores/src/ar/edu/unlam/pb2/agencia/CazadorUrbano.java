package ar.edu.unlam.pb2.agencia;

public class CazadorUrbano extends Cazador {
    public CazadorUrbano(String nombre, Integer experiencia) {
        super(nombre, experiencia);
    }

    @Override
    public boolean puedeCapturar(Profugo p) {
        return this.experiencia > p.getNivelInocencia() && !p.isNervioso();
    }
    
    @Override
    public void intimidar(Profugo p) {
        p.setNervioso(false);
    }
}
