package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class CazadorTest {

    @Test
    public void queElCazadorUrbanoCaptureCorrectamente() {
        Cazador c = new CazadorUrbano("Urbanito");
        c.experiencia = 50; 

        Profugo p = new Profugo("Jose", 30, 20, false); 
        assertTrue(c.puedeCapturar(p));
    }

    @Test
    public void queElCazadorRuralNoPuedaCapturarSiNoEsNervioso() {
        Cazador c = new CazadorRural("Ruralo");
        c.experiencia = 80;

        Profugo p = new Profugo("Pedro", 40, 60, false); // 

        assertFalse(c.puedeCapturar(p));
    }

    @Test
    public void queElCazadorSigilosoIntimideYReduzcaHabilidad() {
        Cazador c = new CazadorSigiloso("Sigilín");
        c.experiencia = 30;

        Profugo p = new Profugo("Sombra", 55, 10, true); // 

        c.intimidarProfugo(p);

        assertEquals(8, p.getNivelInocencia().intValue()); // 
        assertEquals(50, p.getNivelHabilidad().intValue()); // 
    }

    @Test
    public void queLaExperienciaNoSupere100() {
        Cazador c = new CazadorUrbano("Limite");
        c.experiencia = 95;

        Profugo p1 = new Profugo("A", 10, 10, true);
        Profugo p2 = new Profugo("B", 10, 10, true);

        c.incrementarExperiencia(Arrays.asList(p1, p2), 3); 

        assertEquals(100, c.getExperiencia().intValue());
    }

    @Test
    public void queUnCazadorRealiceUnaCapturaExitosa() {
        Zona z = new Zona("Ciudad");
        Profugo p1 = new Profugo("Juan", 40, 10, false); 
        Profugo p2 = new Profugo("Carlos", 60, 30, true); 
        z.agregarProfugo(p1);
        z.agregarProfugo(p2);

        Cazador c = new CazadorUrbano("Max");
        c.experiencia = 20;

        c.partirALaZonaDeCaptura(z);
        boolean resultado = c.buscarProfugo();

        assertTrue(resultado); 
        assertEquals(1, z.getProfugos().size()); 
        assertTrue(z.getProfugos().contains(p2));
    }
}
