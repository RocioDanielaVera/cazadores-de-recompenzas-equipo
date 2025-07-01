package ar.edu.unlam.pb2.agencia;

public class CazadorRural extends Cazador {

    public CazadorRural(String nombre, Integer experiencia) {
        super(nombre, experiencia);
    }

    @Override
    public Boolean puedeCapturar(Profugo profugo) {
        return this.experiencia > profugo.getNivelInocencia() && profugo.isNervioso();
    }
    
    @Override
    public void intimidar(Profugo profugo) {
        profugo.setNervioso(true);
    }
}
