package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;

import org.junit.Test;

public class ZonaTest {

	@Test
	public void queSePuedaIngresar3ProfugosAUnaZonaYaExistente() {
		Profugo martin = new Profugo("Martin Sanchez", 50, 30, false);
		Profugo romina = new Profugo("Romina Olpes", 50, 30, false);
		Profugo lopez = new Profugo("Lopez Caputo", 1, 3, true);
		Zona santelon = new Zona("Pueblo Santelon");
		assertTrue(santelon.agregarProfugo(martin));
		assertTrue(santelon.agregarProfugo(lopez));
		assertTrue(santelon.agregarProfugo(romina));
		assertEquals(Integer.valueOf(3), santelon.getCantidadDeProfugos());
	}

	@Test
	public void queNoSePuedaIngresarUnProfugoConElMismoNombreNiUnProfugoNuloALaColeccionDeProfugosDeLaZona() {
		Profugo martin = new Profugo("Martin Sanchez", 50, 30, false);
		Profugo martinInocente = new Profugo("Martin Sanchez", 10, 30, true);
		Zona santelon = new Zona("Pueblo Santelon");

		assertTrue(santelon.agregarProfugo(martin));

		assertFalse(santelon.agregarProfugo(martinInocente));
		assertFalse(santelon.agregarProfugo(null));

		assertEquals(Integer.valueOf(1), santelon.getCantidadDeProfugos());
	}

	@Test
	public void queUnProfugoCapturadoSeaEliminadoDeLaColeccionDeProfugosDeLaZona() {
		Profugo martin = new Profugo("Martin Sanchez", 50, 30, false);
		Profugo romina = new Profugo("Romina Olpes", 50, 30, false);
		Zona santelon = new Zona("Pueblo Santelon");

		assertTrue(santelon.agregarProfugo(martin));
		assertTrue(santelon.agregarProfugo(romina));
		assertEquals(Integer.valueOf(2), santelon.getCantidadDeProfugos());

		assertTrue(santelon.profugoCapturado(martin));
		assertEquals(Integer.valueOf(1), santelon.getCantidadDeProfugos());
	}

	@Test
	public void queNoSePuedaEliminarUnProfugoQueNoEstaEnLaColeccionDeProfugosDeLaZona() {
		Profugo martin = new Profugo("Martin Sanchez", 50, 30, false);
		Profugo romina = new Profugo("Romina Olpes", 50, 30, false);
		Zona santelon = new Zona("Pueblo Santelon");
		assertTrue(santelon.agregarProfugo(martin));
		assertFalse(santelon.profugoCapturado(romina));
	}

}
