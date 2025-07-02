package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProfugoTest {
	
	private Profugo santiago;
	private Profugo tomas;
	private Profugo luciano;
	private Profugo lautaro;
	
	@Before
	public void setUp() {
		santiago = new Profugo("Santiago Montez", 10, 90, true);
		tomas = new Profugo("Tomas Montez", 30, 20, true);
		luciano = new Profugo("Santiago Montez", 95, 50, true);
		lautaro = new Profugo("Santiago Montez", 500, 500, true);
	}
	
	
	@Test(expected = ValorNoValidoRException.class)
	public void siSeIntentaCrearUnCazadorConHabilidad500SuHabilidadPasaA100_Y_SiSeCreaUnCazadorConHabilidadNegativaSeLanzaUnaRuntimeException() {
		assertEquals(Integer.valueOf(100), lautaro.getNivelHabilidad());
		
		Profugo tobias = new Profugo("Tomas", -10, 50, true);
	}

	@Test(expected = ValorNoValidoRException.class)
	public void siSeIntentaCrearUnCazadorConInocencia500SuInocenciaPasaA100_Y_SiSeCreaUnCazadorConInocenciaNegativaSeLanzaUnaRuntimeExceptionPorValorNoValido() {
		assertEquals(Integer.valueOf(100), lautaro.getNivelInocencia());
		
		Profugo tobias = new Profugo("Tomas", 10, -50, true);
	}


	@Test(expected = ValorNoValidoRException.class)
	public void siAlRestarHabilidad11AUnProfugoDeHabilidad10QuedaEn1_SiSePasaUnMenorCeroOUnNuloSeLanzaUnaRuntimeException() {
		santiago.perderNivelDeHabilidad(11);
		assertEquals(Integer.valueOf(1), santiago.getNivelHabilidad());
		santiago.perderNivelDeHabilidad(-5);
	}
	
	@Test
	public void siHayDosProfugosConElMismoNombreSeConsideranIguales_SiTienenNombresDiferentesSonDiferentes() {
	    santiago = new Profugo("Santiago Montez", 10, 90, true);
		Profugo santi = new Profugo("Santiago Montez", 10, 90, true);
		Profugo rama = new Profugo("Ramon Lopez", 10, 90, true);
		assertEquals(santi, santiago);
		assertFalse(santi.equals(rama));
	}
	
	@Test
	public void queAlPerderNivelDeInocencia50_AUnProfugoDeInocencia50_ElValorPaseAUno() {
		tomas.perderNivelDeInocencia(50);
		assertEquals(Integer.valueOf(1), tomas.getNivelInocencia());
	}
	
	@Test
	public void queAlPerderNivelDeInocencia50_AUnProfugoDeInocencia100_ElValorPaseA50() {
		Profugo santiago = new Profugo("Santiago Montez", 95, 100, true);
		santiago.perderNivelDeInocencia(50);
		assertEquals(Integer.valueOf(50), santiago.getNivelInocencia());
	}
	
	@Test(expected = ValorNoValidoRException.class)
	public void siAUnProfugoDeInocencia50_AlPerderInocencia_SeLePasaUnMenorACeroSeLanzaUnaRuntimeException() {
		luciano.perderNivelDeInocencia(-50);
	}

	@Test
	public void queUnProfugoConHabilidad30PuedaEntrenarEnArtesMarcialesYSuHabilidadPaseA60_PeroQueNoPuedaPasarDe100() {
		Profugo santiago = new Profugo("Santiago Montez", 30, 30, true);
		santiago.entrenarEnArtesMarciales();
		assertEquals(Integer.valueOf(60), santiago.getNivelHabilidad());
		
		santiago.entrenarEnArtesMarciales();
		assertEquals(Integer.valueOf(100), santiago.getNivelHabilidad());
		santiago.entrenarEnArtesMarciales();
		santiago.entrenarEnArtesMarciales();
		assertEquals(Integer.valueOf(100), santiago.getNivelHabilidad());
	}

	@Test
	public void siUnProfugoConInocencia20EntrenaConProteccionLegalSuInocenciaPasaA40YNoPuedeBajarDeEseNivel() {
		tomas.entrenarConProteccionLegal();
		assertEquals(Integer.valueOf(40), tomas.getNivelInocencia());
		tomas.perderNivelDeInocencia(5);
		tomas.perderNivelDeInocencia(5);
		tomas.perderNivelDeInocencia(5);
		assertEquals(Integer.valueOf(40), tomas.getNivelInocencia());
	}

	
	@Test
	public void queUnProfugoQueNoEstaEntrenadoComoElitePuedaCambiarSuEstadoDeNervioso() {
		assertTrue(tomas.isNervioso());
		tomas.setNervioso(false);
		assertFalse(tomas.isNervioso());

	}
	@Test
	public void siUnProfugoEsNerviosoCuandoEntrenaComoEliteNuncaMasPuedenSerConcideradoNervioso() {
		tomas.entrenarComoElite();
		assertFalse(tomas.isNervioso());
		tomas.setNervioso(true);
		tomas.setNervioso(true);
		assertFalse(tomas.isNervioso());

	}
	
	
}
