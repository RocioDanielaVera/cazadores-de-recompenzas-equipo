package ar.edu.unlam.pb2.agencia;

public class CazadorRural extends Cazador {

    public CazadorRural(String nombre) {
        super(nombre);
    }

    @Override
    public boolean puedeCapturar(Profugo profugo) {
        return this.experiencia > profugo.getNivelInocencia() && profugo.isNervioso();
    }

    @Override
    public void intimidar(Profugo profugo) {
        profugo.setNervioso(true);
    }
}
