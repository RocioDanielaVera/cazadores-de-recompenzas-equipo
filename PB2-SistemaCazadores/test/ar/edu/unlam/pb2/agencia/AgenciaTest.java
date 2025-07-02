package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;

import java.util.List;
import org.junit.Before;
import org.junit.Test;

public class AgenciaTest {
	private Agencia agencia;
	private Cazador cazadorRural;
	private Zona ramos;
	private Agencia agenciaCentral;
	private Zona bosque;

	@Before
	public void setUp() {
		agencia = new Agencia("Agencia WestBrook");
		cazadorRural = new CazadorRural("Haymich", 30);
		ramos = new Zona("Ramos Mejia");
		agenciaCentral = new Agencia("Central");
		bosque = new Zona("Bosques");
	}

	@Test
	public void queUnCazadorSePuedaRegistrarEnUnaAgencia() throws NroDeLicenciaNoRegistradaException {
		agencia.registrarAUnCazador(00001, cazadorRural);
		assertEquals(agencia.buscarCazador(00001), cazadorRural);
	}

	@Test
	public void queLaAgenciaPuedaEnviarUnCazadorRegistradoAUnaZona() throws NroDeLicenciaNoRegistradaException {
		agencia.registrarAUnCazador(00001, cazadorRural);
		assertTrue(agencia.enviarCazadorAUnaZona(ramos, 00001));

		assertEquals(cazadorRural.getZonaDeActual(), ramos);
	}

	@Test
	public void queNoSePuedaEnviarUnCazadorAUnaZonaQueNoExiste() throws NroDeLicenciaNoRegistradaException {
		agencia.registrarAUnCazador(00001, cazadorRural);
		assertFalse(agencia.enviarCazadorAUnaZona(null, 00001));

	}

	@Test(expected = NroDeLicenciaNoRegistradaException.class)
	public void queSeMuestreUnaExcepcionCuandoLaAgenciaEnviaAUnCazadorConUnNumeroDeLicenciaNoRegistrada()
			throws NroDeLicenciaNoRegistradaException {
		agencia.registrarAUnCazador(00001, cazadorRural);
		assertFalse(agencia.enviarCazadorAUnaZona(ramos, 00003));

	}

	@Test
	public void queLaAgenciaObtengaTodosLosProfugosCapturados() {
		Cazador cazador = new CazadorUrbano("Urbano", 90);
		agenciaCentral.registrarAUnCazador(1, cazador);

		Profugo p1 = new Profugo("Uno", 20, 10, false);
		Profugo p2 = new Profugo("Dos", 30, 10, false);

		bosque.agregarProfugo(p1);
		bosque.agregarProfugo(p2);

		agencia.enviarCazadorAZona(cazador, bosque);
		cazador.realizarProcesoDeCaptura();

		List<Profugo> capturados = agenciaCentral.getProfugosCapturados();

		assertEquals(2, capturados.size());
		assertTrue(capturados.contains(p1));
		assertTrue(capturados.contains(p2));
	}

	@Test
	public void queLaAgenciaObtengaElProfugoMasHabilCapturado() {
		Cazador cazador = new CazadorRural("Ruralito", 100);
		agenciaCentral.registrarAUnCazador(2, cazador);

		Profugo debil = new Profugo("Debil", 40, 10, true);
		Profugo fuerte = new Profugo("Fuerte", 90, 10, true);

		bosque.agregarProfugo(debil);
		bosque.agregarProfugo(fuerte);

		agenciaCentral.enviarCazadorAZona(cazador, bosque);
		cazador.realizarProcesoDeCaptura();

		Profugo resultado = agenciaCentral.getProfugoMasHabilCapturado();

		assertNotNull(resultado);
		assertEquals(fuerte, resultado);
	}

	@Test
	public void queLaAgenciaObtengaElCazadorConMasCapturas() {

		Cazador c1 = new CazadorUrbano("Pedro", 90);
		Cazador c2 = new CazadorSigiloso("Luis", 90);
		agenciaCentral.registrarAUnCazador(10, c1);
		agencia.registrarAUnCazador(11, c2);

		bosque.agregarProfugo(new Profugo("A", 30, 10, false));
		bosque.agregarProfugo(new Profugo("B", 20, 15, false));
		bosque.agregarProfugo(new Profugo("C", 10, 20, false));

		ramos.agregarProfugo(new Profugo("D", 30, 10, true));

		agenciaCentral.enviarCazadorAZona(c1, bosque);
		c1.realizarProcesoDeCaptura();

		agencia.enviarCazadorAZona(c2, ramos);
		c2.realizarProcesoDeCaptura();

		Cazador resultado = agenciaCentral.getCazadorConMasCapturas();
		assertEquals("Pedro", resultado.getNombre());
	}

}
