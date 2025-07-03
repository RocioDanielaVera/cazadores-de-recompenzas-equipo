package ar.edu.unlam.pb2.agencia;

import static org.junit.Assert.*;

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

		assertEquals(Integer.valueOf(8), p.getNivelInocencia()); //
		assertEquals(Integer.valueOf(50), p.getNivelHabilidad()); //
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
		assertEquals(Integer.valueOf(1), c.getCantidadDeCapturas());
		assertEquals(Integer.valueOf(100), c.getExperiencia());
	}

	@Test
	public void siUnaZonaTieneDosProfugosYUnCazadorUrbanoLograCapturarAUnoLaZonaPasaAtener1Profugo() throws ProfugoNoEncontrado {
		Zona z = new Zona("Ciudad");
		Profugo p1 = new Profugo("Juan", 40, 10, false);
		Profugo p2 = new Profugo("Carlos", 60, 30, true);
		z.agregarProfugo(p1);
		z.agregarProfugo(p2);

		Cazador c = new CazadorUrbano("Max", 50);

		c.partirALaZonaDeCaptura(z);

		assertTrue(c.realizarProcesoDeCaptura());
		assertEquals(Integer.valueOf(1), c.getCantidadDeCapturas());
		assertEquals(Integer.valueOf(1), z.getCantidadDeProfugos());
		assertEquals(p2, z.buscarProfugo(p2));
	}

	@Test
	public void queUnCazadorNoPuedaRealizarUnReporteSiNoEstaRegistradoAUnaAgencia() throws ProfugoNoEncontrado {
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

	@Test
	public void queUnCazadorFalleAlRealizarUnProcesoDeCapturaAUnaZonaVacia() {
		Agencia agencia = new Agencia("Zona Fantasma");
		Zona vacia = new Zona("Sin Nadie");
		Cazador cazador = new CazadorSigiloso("Solo", 100);
		agencia.registrarAUnCazador(111, cazador);

		agencia.enviarCazadorAZona(cazador, vacia);
		assertFalse(cazador.realizarProcesoDeCaptura());
	}

	@Test
	public void queNoSePuedaRealizarUnProcesoDeCapturaSiElCazadorNoSeEncuentraEnUnaZonaEspecifica() {
		Cazador c = new CazadorUrbano("Zero", 5);
		assertFalse(c.realizarProcesoDeCaptura());
	}

	@Test
	public void queSePuedaSumarExperienciaPeroNuncaSobrePasar100() {
		Cazador c = new CazadorUrbano("Zero", 50);
		c.seSumaExperiencia(60);
		assertEquals(Integer.valueOf(100), c.getExperiencia());
	}

	@Test
	public void queSePuedaSumarExperiencia10AUnCazadorConExperiencia50YPaseA60() {
		Cazador c = new CazadorUrbano("Zero", 50);
		c.seSumaExperiencia(10);
		assertEquals(Integer.valueOf(60), c.getExperiencia());
	}

	@Test(expected = ValorNoValidoRException.class)
	public void queLaExperienciaDelCazadorNuncaSeInicieConUnValorNegativo() {
		Cazador c = new CazadorUrbano("Zero", -50);
	}

	@Test
	public void queLaFechaDeCapturaSeGenereAlCrearUnReporte() {
		Cazador cazador = new CazadorRural("Fecha", 50);
		Profugo profugo = new Profugo("Marcelo", 40, 30, true);
		Zona zona = new Zona("Temporaria");

		Reporte reporte = new Reporte(cazador, profugo, zona);
		assertNotNull(reporte.getFechaCaptura());
	}

	@Test
	public void queElCazadorRuralIntimideYPongaNerviosoAlProfugo() {
		Cazador cazador = new CazadorRural("Ruralito", 50);
		Profugo profugo = new Profugo("Ignacio", 40, 20, false);

		assertFalse(profugo.isNervioso());
		cazador.intimidarProfugo(profugo);
		assertTrue(profugo.isNervioso());
	}
	
	@Test
	public void queElCazadorPuedaCazar3ProfugosInclusoSiNoEstaRegistradoEnUnaAgencia() {
		Cazador cazador = new CazadorRural("Ruralito", 50);
		
		Profugo lucas = new Profugo("Lucas", 40, 20, true);
		Profugo matias = new Profugo("Matias", 40, 20, true);
		Profugo romina = new Profugo("Romina", 40, 20, true);
		
		Zona selva = new Zona("Selva");
		
		selva.agregarProfugo(lucas);
		selva.agregarProfugo(matias);
		selva.agregarProfugo(romina);
		
		cazador.partirALaZonaDeCaptura(selva);
		assertTrue(cazador.realizarProcesoDeCaptura());
		assertEquals(Integer.valueOf(3), cazador.getCantidadDeCapturas());
	}


}
