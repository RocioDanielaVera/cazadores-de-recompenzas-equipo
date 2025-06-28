package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;
import org.junit.Test;

public class AgenciaTest {

    @Test
    public void queUnCazadorUrbanoPuedaCapturarProfugoNoNervioso() {
        Profugo p = new Profugo("Juan", 30, 10, false);
        Cazador c = new CazadorUrbano("Mario");
        c.experiencia = 15;

        assertTrue(c.puedeCapturar(p));
    }
}
