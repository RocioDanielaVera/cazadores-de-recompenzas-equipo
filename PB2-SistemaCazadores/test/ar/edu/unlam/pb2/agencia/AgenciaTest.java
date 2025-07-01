package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class AgenciaTest {

	@Test
	public void queUnCazadorSePuedaRegistrarEnUnaAgencia() throws NroDeLicenciaNoRegistradaException {
		Agencia agen = new Agencia("Agencia WestBrook");
		Cazador cazadorRural = new CazadorRural("Haymich", 30);
		agen.registrarAUnCazador(00001, cazadorRural);
		assertEquals(agen.buscarCazador(00001), cazadorRural);
	}

	@Test
	public void queLaAgenciaPuedaEnviarUnCazadorRegistradoAUnaZona() throws NroDeLicenciaNoRegistradaException {
		Agencia agencia = new Agencia("Agencia WestBrook");
		Cazador cazadorRural = new CazadorRural("Haymich", 30);
		agencia.registrarAUnCazador(00001, cazadorRural);

		Zona ramos = new Zona("Ramos Mejia");
		assertTrue(agencia.enviarCazadorAUnaZona(ramos, 00001));

		assertEquals(cazadorRural.getZonaDeActual(), ramos);
	}

	@Test
	public void queNoSePuedaEnviarUnCazadorAUnaZonaQueNoExiste() {
		Agencia agencia = new Agencia("Agencia WestBrook");
		Cazador cazadorRural = new CazadorRural("Haymich", 30);
		agencia.registrarAUnCazador(00001, cazadorRural);

		try {
			assertFalse(agencia.enviarCazadorAUnaZona(null, 00001));
		} catch (NroDeLicenciaNoRegistradaException e) {
			e.printStackTrace();
		}
	}

	@Test(expected = NroDeLicenciaNoRegistradaException.class)
	public void queSeMuestreUnaExcepcionCuandoLaAgenciaEnviaAUnCazadorConUnNumeroDeLicenciaNoRegistrada()
			throws NroDeLicenciaNoRegistradaException {
		Agencia agencia = new Agencia("Agencia WestBrook");
		Cazador cazadorRural = new CazadorRural("Haymich", 30);
		Zona ramos = new Zona("Ramos Mejia");
		agencia.registrarAUnCazador(00001, cazadorRural);

		agencia.enviarCazadorAUnaZona(ramos, 00003);
	}

	@Test
	public void queLaAgenciaObtengaTodosLosProfugosCapturados() {
		Agencia agencia = new Agencia("Central");
		Zona zona = new Zona("Selva");
		Cazador cazador = new CazadorUrbano("Urbano", 90);
		agencia.registrarAUnCazador(1, cazador);

		Profugo p1 = new Profugo("Uno", 20, 10, false);
		Profugo p2 = new Profugo("Dos", 30, 10, false);

		zona.agregarProfugo(p1);
		zona.agregarProfugo(p2);

		agencia.enviarCazadorAZona(cazador, zona);
		cazador.realizarProcesoDeCaptura();

		List<Profugo> capturados = agencia.getProfugosCapturados();

		assertEquals(2, capturados.size());
		assertTrue(capturados.contains(p1));
		assertTrue(capturados.contains(p2));
	}

	@Test
	public void queLaAgenciaObtengaElProfugoMasHabilCapturado() {
		Agencia agencia = new Agencia("Central");
		Zona zona = new Zona("Bosque");
		Cazador cazador = new CazadorRural("Ruralito", 100);
		agencia.registrarAUnCazador(2, cazador);

		Profugo debil = new Profugo("Debil", 40, 10, true);
		Profugo fuerte = new Profugo("Fuerte", 90, 10, true);

		zona.agregarProfugo(debil);
		zona.agregarProfugo(fuerte);

		agencia.enviarCazadorAZona(cazador, zona);
		cazador.realizarProcesoDeCaptura();

		Profugo resultado = agencia.getProfugoMasHabilCapturado();

		assertNotNull(resultado);
		assertEquals("Fuerte", resultado.getNombre());
	}

	@Test
	public void queLaAgenciaObtengaElCazadorConMasCapturas() {
		Agencia agencia = new Agencia("Central");
		Zona zona1 = new Zona("Zona1");
		Zona zona2 = new Zona("Zona2");

		Cazador c1 = new CazadorUrbano("Pedro", 90);
		Cazador c2 = new CazadorSigiloso("Luis", 90);
		agencia.registrarAUnCazador(10, c1);
		agencia.registrarAUnCazador(11, c2);

		zona1.agregarProfugo(new Profugo("A", 30, 10, false));
		zona1.agregarProfugo(new Profugo("B", 20, 15, false));
		zona1.agregarProfugo(new Profugo("C", 10, 20, false));

		zona2.agregarProfugo(new Profugo("D", 30, 10, true));

		agencia.enviarCazadorAZona(c1, zona1);
		c1.realizarProcesoDeCaptura();

		agencia.enviarCazadorAZona(c2, zona2);
		c2.realizarProcesoDeCaptura();

		Cazador resultado = agencia.getCazadorConMasCapturas();
		assertEquals("Pedro", resultado.getNombre());
	}

}
