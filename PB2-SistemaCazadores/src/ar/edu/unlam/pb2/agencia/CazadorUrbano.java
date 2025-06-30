package ar.edu.unlam.pb2.agencia;

public class CazadorUrbano extends Cazador {

    public CazadorUrbano(String nombre) {
        super(nombre);
    }

    @Override
    public Boolean puedeCapturar(Profugo profugo) {
        return this.experiencia > profugo.getNivelInocencia() && !profugo.isNervioso();
    }

    @Override
    public void intimidar(Profugo profugo) {
        profugo.setNervioso(false);
    }
}
