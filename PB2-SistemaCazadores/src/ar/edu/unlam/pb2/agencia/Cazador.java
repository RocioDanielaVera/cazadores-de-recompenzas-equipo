package ar.edu.unlam.pb2.agencia;

import java.util.*;

public abstract class Cazador {

	protected String nombre;
	protected Integer experiencia = 1;
	protected Zona zonaActual = null;
	protected Agencia agencia = null;
	private final Integer LIMITE_DE_EXPERIENCIA = 100;

	public Cazador(String nombre, Integer experiencia) {
		this.nombre = nombre;
		this.validarIngresoDeExperiencia(experiencia);
	}

	public void registrarseEnUnaAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

	public void partirALaZonaDeCaptura(Zona zona) {
		this.zonaActual = zona;
	}

	public Boolean realizarProcesoDeCaptura() {
		if (zonaActual == null)
			return false;
		
		List<Profugo> profugos = zonaActual.getProfugos();
		List<Profugo> capturados = new ArrayList<>();
		List<Profugo> intimidados = new ArrayList<>();

		for (Profugo p : profugos) {
			if (this.puedeCapturar(p)) {
				Reporte nuevo = new Reporte(this, p, this.zonaActual);
				this.realizarReporte(nuevo);
				zonaActual.profugoCapturado(p);
				capturados.add(p);
			} else {
				this.intimidarProfugo(p);
				intimidados.add(p);
			}
		}

		Boolean sePudoSumarExperiencia = incrementarExperiencia(getHabilidadMinimaDeIntimidados(intimidados),
				capturados);

		return !capturados.isEmpty() && sePudoSumarExperiencia;
	}

	public void intimidarProfugo(Profugo profugo) {
		profugo.perderNivelDeInocencia(2);
		this.intimidar(profugo);
	}

	public Boolean realizarReporte(Reporte reporte) {
		if (this.agencia != null) {
			agencia.enviarReporteALaLista(reporte);
			return true;
		}
		return false;
	}
	
	public Integer getHabilidadMinimaDeIntimidados(List<Profugo> intimidados) {
		if (!intimidados.isEmpty()) {
			Integer minHabilidad = intimidados.stream().mapToInt(Profugo::getNivelHabilidad).min().orElse(0);
			return minHabilidad;
		}
		return 0;
	}
	
	

	public Boolean incrementarExperiencia(Integer minHabilidad, List<Profugo> capturados) {
		if (!capturados.isEmpty()) {
			Integer experienciaGanada = minHabilidad + (2 * capturados.size());
			seSumaExperiencia(experienciaGanada);
			return true;
		}
		return false;
	}
	
	private void validarIngresoDeExperiencia(Integer experiencia) {
		if(experiencia <= 0 || experiencia == null) {
			throw new ValorNoValidoRException("Valor ingresado no valido. Deber ser un numero entre 1 y 100.");
		}else if(experiencia >= 100) {
			this.experiencia = 100;
		}else {
			this.experiencia = experiencia;
		}
	}

	public void seSumaExperiencia(Integer experiencia) {
		if ((this.experiencia + experiencia) <= this.LIMITE_DE_EXPERIENCIA) {
			this.experiencia += experiencia;
		} else if (((this.experiencia + experiencia) >= this.LIMITE_DE_EXPERIENCIA)) {
			this.experiencia = this.LIMITE_DE_EXPERIENCIA;
		}
		
	}

	public Integer getExperiencia() {
		return this.experiencia;
	}

	public abstract Boolean puedeCapturar(Profugo profugo);

	public abstract void intimidar(Profugo profugo);

	public Zona getZonaDeActual() {
		return zonaActual;
	}

	public String getNombre() {
		return this.nombre;
	}

}
