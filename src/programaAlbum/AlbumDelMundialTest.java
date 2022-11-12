package programaAlbum;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AlbumDelMundialTest {
	
	private static final String TIPO_TRADICIONAL = "Tradicional";
	private static final String TIPO_EXTENDIDO = "Extendido";
	private static final String TIPO_WEB = "Web";
	
	
	IAlbumDelMundial sistema;
	private int dniConAlbumTradicional;
	private int dniConAlbumWeb;

	@Before
	public void setUp() throws Exception {
		sistema = new AlbumDelMundial();
		sistema.registrarParticipante(1111, "Andres", TIPO_TRADICIONAL);
		dniConAlbumTradicional = 1111;
		
		sistema.registrarParticipante(2222, "Samanta", TIPO_WEB);
		dniConAlbumWeb = 2222;
		
		sistema.registrarParticipante(3333, "Juana", TIPO_EXTENDIDO);
		
		sistema.registrarParticipante(4444, "Pancho", TIPO_TRADICIONAL);
		
		sistema.comprarFiguritas(1111);
		sistema.comprarFiguritas(4444);
		
	}
	
	// Registrar participante
	
	@Test(expected=RuntimeException.class)
	public void t01_registrarParticianteConTipoInvalido_GeneraError() {
		sistema.registrarParticipante(5555, "Ricardo", "Especial");
	}

	@Test(expected=RuntimeException.class)
	public void t02_registrarParticipanteRepetido_GeneraError() {
		sistema.registrarParticipante(1111, "Roberto", TIPO_TRADICIONAL);
	}
	
	// Comprar figuritas
	
	@Test(expected=RuntimeException.class)
	public void t03_comprarFiguritasConParticipanteNoRegistrado_GeneraError() {
		sistema.comprarFiguritas(5555);
	}
	
	@Test(expected=RuntimeException.class)
	public void t04_comprarFiguritasTop10ConAlbumWeb_GeneraError() {
		sistema.comprarFiguritasTop10(dniConAlbumWeb);
	}
	
	@Test(expected=RuntimeException.class)
	public void t05_comprarFiguritasTop10ConAlbumTradicional_GeneraError() {
		sistema.comprarFiguritasTop10(dniConAlbumTradicional);
	}
	
	@Test
	public void t06_cambiarCodigoPromocionaConAlbumWeb_SinErrores() {
		sistema.comprarFiguritasConCodigoPromocional(dniConAlbumWeb);
	}
	
	@Test(expected=RuntimeException.class)
	public void t07_cambiarCodigoPromocionaSinAlbumWeb_GeneraError() {
		sistema.comprarFiguritasConCodigoPromocional(dniConAlbumTradicional);
	}
	
	// Aplicar Sorteo instantaneo de Album tradicional
	
	@Test
	public void t08_aplicarSorteoInstantaneoConAlbumTradicional_DevuelveUnPremio() {
		String premio = sistema.aplicarSorteoInstantaneo(dniConAlbumTradicional);
		assertFalse(premio.isEmpty());
	}
	
	@Test(expected=RuntimeException.class)
	public void t09_aplicarSorteoInstantaneoConAlbumWeb_GeneraError() {
		sistema.aplicarSorteoInstantaneo(dniConAlbumWeb);
	}
	
	// Intercambiar Figuritas
	
	@Test
	public void t10_intercambiarFiguritasConAlbumVacio_DevuelveTrue() {
		// Para este caso se registraron 2 tradicionales 
		// y ambos compraron figuritas.
		assertTrue(sistema.intercambiarUnaFiguritaRepetida(dniConAlbumTradicional));
	}
	
	@Test
	public void t11_intercambiarFiguritasSinFiguritas_DevuelveFalse() {
		assertFalse(sistema.intercambiarUnaFiguritaRepetida(dniConAlbumWeb));
	}

	// Pegar figuritas en el Album 

	@Test
	public void t12_pegarFiguritasPorPrimeraVez_DevuelveListaNoVacia() {
		List<String> figuritasPegadas = sistema.pegarFiguritas(dniConAlbumTradicional);
		assertNotEquals(0, figuritasPegadas.size());
	}
	
	@Test
	public void t13_pegarFiguritasSinTenerFiguritas_DevuelveListaVacia() {
		List<String> figuritasPegadas = sistema.pegarFiguritas(dniConAlbumWeb);
		assertEquals(0, figuritasPegadas.size());
	}
	
	// Lleno Album
	
	@Test
	public void t14_llenoAlbumConParticipanteNuevo_DevuelveFalse() {
		assertFalse(sistema.llenoAlbum(dniConAlbumWeb));
	}
	
	// Consultar por participante puntual
	
	@Test
	public void t15_darNombreConDniRegistrado_DevuelveNombre() {
		assertEquals("Andres", sistema.darNombre(1111));
	}
	@Test(expected=RuntimeException.class)
	public void t16_darNombreConDniNoRegistrado_GeneraError() {
		assertEquals("Ricardo", sistema.darNombre(5555));
	}
	
	@Test(expected=RuntimeException.class)
	public void t17_darPremioConDniNoRegistrado_GeneraError() {
		sistema.darPremio(5555);
	}
	
	@Test(expected=RuntimeException.class)
	public void t18_darPremioConParticipanteNuevo_GeneraError() {
		sistema.darPremio(dniConAlbumTradicional);
	}
	
	// Listado de ganadores
	
	@Test
	public void t19_consultarListadoDeGanadoresConJuegoRecienEmpezado_DevuelveStringVacio() {
		assertEquals("", sistema.listadoDeGanadores());
	}
	
	// Listado de ganadores
	
	@Test
	public void t20_consultarParticipantesQueCompletaronArgentinaConJuegoRecienEmpezado_DevuelveListaVacia() {
		List<String> completaronPais=sistema.participantesQueCompletaronElPais("Argentina");
		assertEquals(0, completaronPais.size());
	}
}