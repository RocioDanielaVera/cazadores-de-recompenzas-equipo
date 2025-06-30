package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;
import org.junit.Test;

public class AgenciaTest {

    @Test
    public void queUnCazadorSePuedaRegistrarEnUnaAgencia() throws NroDeLicenciaNoRegistradaException  {
    	Agencia agen = new Agencia("Agencia WestBrook");
    	Cazador cazadorRural = new CazadorRural("Haymich");
    	agen.registrarAUnCazador(00001, cazadorRural);
    	assertEquals(agen.buscarCazador(00001), cazadorRural);
    }
    
    @Test
    public void queLaAgenciaPuedaEnviarUnCazadorRegistradoAUnaZona() throws NroDeLicenciaNoRegistradaException {
    	Agencia agencia= new Agencia("Agencia WestBrook");
    	Cazador cazadorRural = new CazadorRural("Haymich");
    	agencia.registrarAUnCazador(00001, cazadorRural);
    	
    	Zona ramos = new Zona("Ramos Mejia");
		assertTrue(agencia.enviarCazadorAUnaZona(ramos, 00001));
  
    	assertEquals(cazadorRural.getZonaDeActual(), ramos);
    }
    
    @Test 
    public void queNoSePuedaEnviarUnCazadorAUnaZonaQueNoExiste() {
    	Agencia agencia = new Agencia("Agencia WestBrook");
    	Cazador cazadorRural = new CazadorRural("Haymich");	
    	agencia.registrarAUnCazador(00001, cazadorRural);
    	
    	try {
    		assertFalse(agencia.enviarCazadorAUnaZona(null, 00001));
		} catch (NroDeLicenciaNoRegistradaException e) {
			e.printStackTrace();
		}
    }
    
    @Test (expected = NroDeLicenciaNoRegistradaException.class)
    public void queSeMuestreUnaExcepcionCuandoLaAgenciaEnviaAUnCazadorConUnNumeroDeLicenciaNoRegistrada() throws NroDeLicenciaNoRegistradaException {
    	Agencia agencia = new Agencia("Agencia WestBrook");
    	Cazador cazadorRural = new CazadorRural("Haymich");
    	Zona ramos = new Zona("Ramos Mejia");
    	agencia.registrarAUnCazador(00001, cazadorRural);
    
    	agencia.enviarCazadorAUnaZona(ramos, 00003);
    }
    
    

    
    
}
