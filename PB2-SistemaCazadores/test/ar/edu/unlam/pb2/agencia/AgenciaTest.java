package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;
import org.junit.Test;

public class AgenciaTest {

    @Test
    public void queUnCazadorSePuedaRegistrarEnUnaAgencia() {
    	Agencia agen = new Agencia("Agencia WestBrook");
    	Cazador cazadorRural = new CazadorRural("Haymich", 25);
    	agen.registrarAUnCazador(00001, cazadorRural);
    	assertEquals(agen.buscarCazador(00001), cazadorRural);
    }
    
    
    @Test
    public void queLaAgenciaPuedaEnviarUnCazadorRegistradoAUnaZona() {
    	Agencia agencia= new Agencia("Agencia WestBrook");
    	Cazador cazadorRural = new CazadorRural("Haymich", 25);
    	agencia.registrarAUnCazador(00001, cazadorRural);
    	
    	Zona ramos = new Zona("Ramos Mejia");
    	try {
    		assertTrue(agencia.enviarCazadorAUnaZona(ramos, 00001));
		} catch (NroDeLicenciaNoRegistradaException e) {
			e.printStackTrace();
		}
    	assertEquals(cazadorRural.getZonaDeCaptura(), ramos);
    }
    
    @Test 
    public void queNoSePuedaEnviarUnCazadorAUnaZonaQueNoExiste() {
    	Agencia agencia = new Agencia("Agencia WestBrook");
    	Cazador cazadorRural = new CazadorRural("Haymich", 25);	
    	agencia.registrarAUnCazador(00001, cazadorRural);
    	
    	try {
    		assertFalse(agencia.enviarCazadorAUnaZona(null, 00001));
		} catch (NroDeLicenciaNoRegistradaException e) {
			e.printStackTrace();
		}
    }
    
    @Test (expected = NroDeLicenciaNoRegistradaException.class)
    public void queSeMuestreUnaExcepcionCuandoLaAgenciaEnviaAUnCazadorConUnNumeroDeLicenciaNoRegistrada() 
    		throws NroDeLicenciaNoRegistradaException {
    	Agencia agencia = new Agencia("Agencia WestBrook");
    	Cazador cazadorRural = new CazadorRural("Haymich", 25);
    	Zona ramos = new Zona("Ramos Mejia");
    	agencia.registrarAUnCazador(00001, cazadorRural);
    
    	agencia.enviarCazadorAUnaZona(ramos, 00003);
    }
    
    

    
    
}
