package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class CazadorTest {
	

	@Test
    public void siSeCreaUnCazadorConExperiencia500_SuExperienciaSera100ComoLimiteDeExperiencia() {
        Cazador c = new CazadorUrbano("Urbanito", 500);
        assertEquals(Integer.valueOf(100), c.getExperiencia());
    }

    @Test
    public void unCazadorUrbanoPodraCapturarAUnProfugoSoloSiEsteNoEsNervioso() {
        Cazador c = new CazadorUrbano("Urbanito", 50);
        Profugo p = new Profugo("Jose", 30, 20, false); 
        Profugo p2 = new Profugo("Lucas", 30, 20, true); 
        assertFalse(p.isNervioso());
        assertTrue(p2.isNervioso());
        
        assertTrue(c.puedeCapturar(p));
        assertFalse(c.puedeCapturar(p2));
    }

    @Test
    public void queElCazadorRuralNoPuedaCapturarSiNoEsNervioso() {
        Cazador c = new CazadorRural("Ruralo", 80);
        Profugo p = new Profugo("Pedro", 40, 60, false); // 

        assertFalse(c.puedeCapturar(p));
    }

    @Test
    public void queElCazadorSigilosoIntimideYReduzcaHabilidad() {
        Cazador c = new CazadorSigiloso("Sigilín", 30);

        Profugo p = new Profugo("Sombra", 55, 10, true); // 

        c.intimidarProfugo(p);

        assertEquals(8, p.getNivelInocencia().intValue()); // 
        assertEquals(50, p.getNivelHabilidad().intValue()); // 
    }

    @Test
    public void queAlRealizarUnProcesoDeCapturaLaExperienciaNoSupere100() {
        Cazador c = new CazadorUrbano("Limite", 95);
        Zona z = new Zona("Ciudad");
        Profugo p1 = new Profugo("A", 10, 10, true);
        Profugo p2 = new Profugo("B", 10, 10, false);
        z.agregarProfugo(p1);
        z.agregarProfugo(p2);
        c.partirALaZonaDeCaptura(z);
        c.realizarProcesoDeCaptura();
        assertEquals(Integer.valueOf(100), c.getExperiencia());
    }

    @Test
    public void siUnaZonaTieneDosProfugosYUnCazadorUrbanoLograCapturarAUnoLaZonaPasaAtener1Profugo() {
        Zona z = new Zona("Ciudad");
        Profugo p1 = new Profugo("Juan", 40, 10, false); 
        Profugo p2 = new Profugo("Carlos", 60, 30, true); 
        z.agregarProfugo(p1);
        z.agregarProfugo(p2);

        Cazador c = new CazadorUrbano("Max", 50);

        c.partirALaZonaDeCaptura(z);
        
        assertTrue(c.realizarProcesoDeCaptura()); 
        assertEquals(1, z.getProfugos().size()); 
        assertTrue(z.getProfugos().contains(p2));
    }
  
    @Test
    public void queUnCazadorNoPuedaRealizarUnReporteSiNoEstaRegistradoAUnaAgencia() {
        Profugo p1 = new Profugo("Juan", 40, 10, false); 
        Zona z = new Zona("Ciudad");
        z.agregarProfugo(p1);
        Cazador c = new CazadorUrbano("Max", 50);
        assertTrue(c.puedeCapturar(p1));
        Reporte nuevo = new Reporte(c, z.buscarProfugo(p1), z);
        assertFalse(c.realizarReporte(nuevo));  
        
        Agencia pompeye = new Agencia("Pompeye");
        pompeye.registrarAUnCazador(56482, c);
        assertTrue(c.realizarReporte(nuevo)); 
        
    }
    
    
    
}
