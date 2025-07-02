package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ZonaTest {

	private Profugo martin;
	private Zona santelon;
	private Profugo romina;
	private Profugo lopez;
	private Profugo martinInocente;

	@Before
	public void setUp() {
		martin = new Profugo("Martin Sanchez", 50, 30, false);
		santelon = new Zona("Pueblo Santelon");
		romina = new Profugo("Romina Olpes", 50, 30, false);
		lopez = new Profugo("Lopez Caputo", 1, 3, true);
		martinInocente = new Profugo("Martin Sanchez", 10, 30, true);
	}

	@Test
	public void queUnProfugoExistenteSeAgregueExitosamenteAUnaZonaEspecifica() throws ProfugoNoEncontrado {
		assertTrue(santelon.agregarProfugo(martin));
		assertEquals(martin, santelon.buscarProfugo(martin));
	}

	@Test
	public void queSePuedaIngresar3ProfugosAUnaZonaYaExistente() {
		assertTrue(santelon.agregarProfugo(martin));
		assertTrue(santelon.agregarProfugo(lopez));
		assertTrue(santelon.agregarProfugo(romina));
		assertEquals(Integer.valueOf(3), santelon.getCantidadDeProfugos());
	}

	@Test
	public void queNoSePuedaIngresarUnProfugoConElMismoNombreALaColeccionDeProfugosDeUnaZona() {
		assertTrue(santelon.agregarProfugo(martin));
		assertFalse(santelon.agregarProfugo(martinInocente));
		assertEquals(Integer.valueOf(1), santelon.getProfugos().size(), 0.01);
	}

	@Test(expected = ValorNoValidoRException.class)
	public void queAlIngresarUnProfugoNuloAUnaZonaSeMuestreUnaRuntimeExceptionPorValorNoValido() {
		santelon.agregarProfugo(null);
	}

	@Test(expected = ProfugoNoEncontrado.class)
	public void queSeMuestreUnaExcepcionAlBuscarUnProfugoQueNoEstaEnUnaZonaEspecifica_Y_CuandoElProfugoSeaNulo()
			throws ProfugoNoEncontrado {
		santelon.buscarProfugo(martinInocente);
		santelon.buscarProfugo(null);
	}

	@Test
	public void queUnProfugoCapturadoSeaEliminadoDeLaColeccionDeProfugosDeLaZona() {
		assertTrue(santelon.agregarProfugo(martin));
		assertTrue(santelon.agregarProfugo(romina));
		assertEquals(Integer.valueOf(2), santelon.getCantidadDeProfugos());

		assertTrue(santelon.profugoCapturado(martin));
		assertEquals(Integer.valueOf(1), santelon.getCantidadDeProfugos());
	}

	@Test
	public void queNoSePuedaEliminarUnProfugoQueNoEstaEnLaColeccionDeProfugosDeLaZona() {
		assertTrue(santelon.agregarProfugo(martin));
		assertFalse(santelon.profugoCapturado(romina));
	}

}
