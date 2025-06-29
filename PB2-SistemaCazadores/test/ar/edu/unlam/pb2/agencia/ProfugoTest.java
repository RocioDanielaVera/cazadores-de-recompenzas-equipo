package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;

import org.junit.Test;

public class ProfugoTest {

	@Test
	public void queAlSumar2DeHabilidad_A_UnProfugoYaExistenteConHabilidad5_SuHabilidadPaseA7() {
		Profugo santiago = new Profugo("Santiago Montez", 5, 90, true);
		santiago.sumarHabilidad(2);
		assertEquals(Integer.valueOf(7), santiago.getNivelHabilidad());
	}
	
	@Test
	public void siAlRestarHabilidadQuedaEnNegativoLaHabilidadDelProfugoQuedaIgualYNoSeResta() {
		Profugo santiago = new Profugo("Santiago Montez", 10, 90, true);
		santiago.restarHabilidad(5);
		assertEquals(Integer.valueOf(5), santiago.getNivelHabilidad());
		santiago.restarHabilidad(8);
		assertEquals(Integer.valueOf(5), santiago.getNivelHabilidad());
	}

	@Test
	public void queSePuedaSumar5_Y_Restar10AUnProfugoDeInocencia50_Y_SuInocenciaPaseA45() {
		Profugo santiago = new Profugo("Santiago Montez", 95, 50, true);
		santiago.sumarInocencia(5);
		santiago.restarInocencia(10);
		assertEquals(Integer.valueOf(45), santiago.getNivelInocencia());
	}

	@Test
	public void queUnProfugoConHabilidad30PuedaEntrenarEnArtesMarcialesYSuHabilidadPaseA60_PeroQueNoPuedaPasarDe100() {
		Profugo santiago = new Profugo("Santiago Montez", 30, 50, true);
		assertTrue(santiago.entrenarEnArtesMarciales());
		assertEquals(Integer.valueOf(60), santiago.getNivelHabilidad());
		
		assertFalse(santiago.entrenarEnArtesMarciales());
	}
	
	@Test
	public void siUnProfugoConInocencia20EntrenaConProteccionLegalSuInocenciaPasaA40YNoPuedeBajarDeEseNivel() {
		Profugo santiago = new Profugo("Santiago Montez", 30, 20, true);
		santiago.entrenarConProteccionLegal();
		assertEquals(Integer.valueOf(40), santiago.getNivelInocencia());
		santiago.restarInocencia(5);
		santiago.restarInocencia(5);
		santiago.restarInocencia(5);
		santiago.restarInocencia(5);
		santiago.restarInocencia(5);
		santiago.restarInocencia(5);
		assertEquals(Integer.valueOf(40), santiago.getNivelInocencia());
		
	}
	
	@Test
	public void siUnProfugoEsNerviosoCuandoEntrenaComoEliteNuncaMasPuedenSerConcideradoNervioso() {
		Profugo santiago = new Profugo("Santiago Montez", 30, 20, true);
		santiago.entrenarComoElite();
		assertFalse(santiago.isNervioso());
		santiago.setNervioso(true);
		santiago.setNervioso(true);
		assertFalse(santiago.isNervioso());
		
	}
}
