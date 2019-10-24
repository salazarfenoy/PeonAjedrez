package org.iesalandalus.programacion.peonajedrez;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.peonajedrez.Peon;
import org.iesalandalus.programacion.peonajedrez.Color;
import org.iesalandalus.programacion.peonajedrez.Direccion;
import org.iesalandalus.programacion.peonajedrez.Posicion;
import org.junit.BeforeClass;
import org.junit.Test;

public class PeonTest {
	
	private static final String COLOR_NO_NULO = "Debería haber saltado una excepción indicando que el color no puede ser nulo.";
	private static final String DIRECCION_NO_NULA = "Debería haber saltado una excepción indicando que no se puede mover en una dirección nula.";
	private static final String CADENA_NO_ESPERADA = "La cadena devuelta no es la esperada.";
	private static final String COLOR_NO_ESPERADO = "El color del peón no es el esperado.";
	private static final String POSICION_NO_ESPERADA = "La posición del peón no es la esperada.";
	private static final String OBJETO_DEBERIA_SER_NULL = "No se debería haber creado el objeto peón.";
	private static final String ERROR_COLOR_NULO = "ERROR: No se puede asignar un color nulo.";
	private static final String ERROR_MOVIMIENTO_NO_VALIDO = "ERROR: Movimiento no válido.";
	private static final String ERROR_DIRECCION_NULA = "ERROR: Mover el peón en una dirección nula no está permitido.";
	private static final String ERROR_DOS_PASOS = "ERROR: El peón sólo se puede mover 2 pasos cuando se encuentra en la casilla inicial.";
	private static final String ERROR_NUMERO_PASOS = "ERROR: El peón sólo se puede mover 1 o 2 pasos.";
	private static final String ERROR_COLUMNA_NO_VALIDA = "ERROR: Columna no válida.";
	private static final String EXCEPCION_MENSAJE_ADECUADO = "Debería haber saltado una excepción con el mensaje adecuado.";
	private static final String EXCEPCION_MOVIMIENTO_NO_VALIDO = "Debería haber saltado una excepción indicando que el movimiento no es válido.";
	private static final String NO_EXCEPCION_MOVIMIENTO_VALIDO = "No debería saltar ninguna excepción ya que los movimientos son válidos.";
	private static final String NO_EXCEPCION = "No debería haber saltado este tipo de excepción.";
	
	private static Posicion posicionDefectoNegro;
	private static Posicion posicionDefectoBlanco;
	
	@BeforeClass
	public static void asignarValoresAtributos() {
		posicionDefectoNegro = new Posicion(7, 'd');
		posicionDefectoBlanco = new Posicion(2, 'd');
	}

	@Test
	public void constructorDefectoCreaPeonNegroEnPosicion7d() {
		Peon peon = new Peon();
		assertThat(COLOR_NO_ESPERADO, peon.getColor(), is(Color.NEGRO));
		assertThat(POSICION_NO_ESPERADA, peon.getPosicion(), is(posicionDefectoNegro));
	}
	
	@Test
	public void constructorColorBlancoCreaPeonBlancoEnPosicion2d() {
		Peon peonBlanco = new Peon(Color.BLANCO);
		assertThat(COLOR_NO_ESPERADO, peonBlanco.getColor(), is(Color.BLANCO));
		assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(posicionDefectoBlanco));
	}
	
	@Test
	public void constructorColorNegroCreaPeonNegroEnPosicion7d() {
		Peon peonNegro = new Peon(Color.NEGRO);
		assertThat(COLOR_NO_ESPERADO, peonNegro.getColor(), is(Color.NEGRO));
		assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(posicionDefectoNegro));
	}
	
	@Test
	public void constructorColorNuloLanzaExcepcion() {
		Peon peon = null;
		try {
			peon = new Peon(null);
			fail(COLOR_NO_NULO);
		} catch (NullPointerException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULL, peon, is(nullValue()));
		}
	}
	
	@Test
	public void constructorColorValidoColumnaValidaCreaPeonCorrectamente() {
		Peon peonBlanco = new Peon(Color.BLANCO, 'a');
		assertThat(COLOR_NO_ESPERADO, peonBlanco.getColor(), is(Color.BLANCO));
		assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(2, 'a')));
		peonBlanco = new Peon(Color.BLANCO, 'h');
		assertThat(COLOR_NO_ESPERADO, peonBlanco.getColor(), is(Color.BLANCO));
		assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(2, 'h')));
		Peon peonNegro = new Peon(Color.NEGRO, 'a');
		assertThat(COLOR_NO_ESPERADO, peonNegro.getColor(), is(Color.NEGRO));
		assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(7, 'a')));
		peonNegro = new Peon(Color.NEGRO, 'h');
		assertThat(COLOR_NO_ESPERADO, peonNegro.getColor(), is(Color.NEGRO));
		assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(7, 'h')));
	}
	
	@Test
	public void constructorColorNuloColumnaValidaLanzaExcepcion() {
		Peon peon = null;
		try {
			peon = new Peon(null, 'g');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (NullPointerException e) {
			assertThat(COLOR_NO_NULO, e.getMessage(), is(ERROR_COLOR_NULO));
			assertThat(OBJETO_DEBERIA_SER_NULL, peon, is(nullValue()));
		}
	}
	
	@Test
	public void constructorColorValidoColumnaNoValidaLanzaExcepcion() {
		Peon peonColumnaNoValida = null;
		try {
			peonColumnaNoValida = new Peon(Color.BLANCO, '`');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, peonColumnaNoValida, is(nullValue()));
		}
		peonColumnaNoValida = null;
		try {
			peonColumnaNoValida = new Peon(Color.BLANCO, 'i');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, peonColumnaNoValida, is(nullValue()));
		}
		peonColumnaNoValida = null;
		try {
			peonColumnaNoValida = new Peon(Color.NEGRO, '`');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, peonColumnaNoValida, is(nullValue()));
		}
		peonColumnaNoValida = null;
		try {
			peonColumnaNoValida = new Peon(Color.NEGRO, 'i');
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (IllegalArgumentException e) {
			assertThat(EXCEPCION_MENSAJE_ADECUADO, e.getMessage(), is(ERROR_COLUMNA_NO_VALIDA));
			assertThat(OBJETO_DEBERIA_SER_NULL, peonColumnaNoValida, is(nullValue()));
		}
	}
	
	@Test
	public void mover1PasoCambiaPosicionCorrectamente() {
		Peon peonBlanco = new Peon(Color.BLANCO);
		Peon peonNegro = new Peon(Color.NEGRO);
		try {
			peonBlanco.mover(1);
			assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(3, 'd')));
			peonNegro.mover(1);
			assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(6, 'd')));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION_MOVIMIENTO_VALIDO);
		}
	}
	
	@Test 
	public void mover1PasoAPosicionNoValidaLanzaExcepcionYNoMuevePeon() {
		Peon peonBlanco = new Peon(Color.BLANCO);
		Peon peonNegro = new Peon(Color.NEGRO);
		try {
			peonBlanco.mover(1);
			peonBlanco.mover(1);
			peonBlanco.mover(1);
			peonBlanco.mover(1);
			peonBlanco.mover(1);
			peonBlanco.mover(1);
			peonBlanco.mover(1);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_MOVIMIENTO_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(8, 'd')));
		}
		try {
			peonNegro.mover(1);
			peonNegro.mover(1);
			peonNegro.mover(1);
			peonNegro.mover(1);
			peonNegro.mover(1);
			peonNegro.mover(1);
			peonNegro.mover(1);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_MOVIMIENTO_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(1, 'd')));
		}
	}
	
	@Test
	public void mover2PasosDesdePoscionInicialCambiaPosicionCorrectamente() {
		Peon peonBlanco = new Peon(Color.BLANCO);
		Peon peonNegro = new Peon(Color.NEGRO);
		try {
			peonBlanco.mover(2);
			assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(4, 'd')));
			peonNegro.mover(2);
			assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(5, 'd')));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION_MOVIMIENTO_VALIDO);
		}
	}
	
	@Test
	public void mover2PasosSinEstarEnPosicionInicialLanzaExcepcionYNoMuevePeon() {
		Peon peonBlanco = new Peon(Color.BLANCO);
		Peon peonNegro = new Peon(Color.NEGRO);
		try {
			peonBlanco.mover(2);
			peonBlanco.mover(2);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_DOS_PASOS));
			assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(4, 'd')));
		}
		try {
			peonNegro.mover(2);
			peonNegro.mover(2);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_DOS_PASOS));
			assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(5, 'd')));
		}
	}
	
	@Test
	public void moverPasosNoValidosLanzaExcepcionYNoMuevePeon() {
		Peon peonBlanco = new Peon(Color.BLANCO);
		Peon peonNegro = new Peon(Color.NEGRO);
		try {
			peonBlanco.mover(3);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_NUMERO_PASOS));
			assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(2, 'd')));
		}
		try {
			peonBlanco.mover(0);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_NUMERO_PASOS));
			assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(2, 'd')));
		}
		try {
			peonNegro.mover(3);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_NUMERO_PASOS));
			assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(7, 'd')));
		}
		try {
			peonNegro.mover(0);
			fail(EXCEPCION_MENSAJE_ADECUADO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_NUMERO_PASOS));
			assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(7, 'd')));
		}
	}
		
	@Test
	public void moverDireccionValidaCambiaPosicionCorrectamente() {
		Peon peonBlanco = new Peon(Color.BLANCO);
		Peon peonNegro = new Peon(Color.NEGRO);
		try {
			peonBlanco.mover(Direccion.IZQUIERDA);
			assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(3, 'c')));
			peonBlanco.mover(Direccion.DERECHA);
			assertThat(POSICION_NO_ESPERADA, peonBlanco.getPosicion(), is(new Posicion(4, 'd')));
			peonNegro.mover(Direccion.IZQUIERDA);
			assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(6, 'c')));
			peonNegro.mover(Direccion.DERECHA);
			assertThat(POSICION_NO_ESPERADA, peonNegro.getPosicion(), is(new Posicion(5, 'd')));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION_MOVIMIENTO_VALIDO);
		}
	}
	
	@Test
	public void moverDireccionNulaLanzaExcepcionYNoMuevePeon() {
		Peon peon = new Peon();
		try {
			peon.mover(null);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (NullPointerException e) {
			assertThat(DIRECCION_NO_NULA, e.getMessage(), is(ERROR_DIRECCION_NULA));
			assertThat(POSICION_NO_ESPERADA, peon.getPosicion(), is(posicionDefectoNegro));
		} catch (OperationNotSupportedException e) {
			fail(NO_EXCEPCION);
		}
	}
	
	@Test
	public void moverDireccionValidaAPosicionNoValidaLanzaExcepcionYNoMuevePeon() {
		Peon peonNegroA = new Peon(Color.NEGRO, 'a');
		Peon peonNegroH = new Peon(Color.NEGRO, 'h');
		Peon peonBlancoA = new Peon(Color.BLANCO, 'a');
		Peon peonBlancoH = new Peon(Color.BLANCO, 'h');
		try {
			peonNegroA.mover(Direccion.IZQUIERDA);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_MOVIMIENTO_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, peonNegroA.getPosicion(), is(new Posicion(7, 'a')));
		}
		try {
			peonNegroH.mover(Direccion.DERECHA);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_MOVIMIENTO_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, peonNegroH.getPosicion(), is(new Posicion(7, 'h')));
		}
		try {
			peonBlancoA.mover(Direccion.IZQUIERDA);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_MOVIMIENTO_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, peonBlancoA.getPosicion(), is(new Posicion(2, 'a')));
		}
		try {
			peonBlancoH.mover(Direccion.DERECHA);
			fail(EXCEPCION_MOVIMIENTO_NO_VALIDO);
		} catch (OperationNotSupportedException e) {
			assertThat(EXCEPCION_MOVIMIENTO_NO_VALIDO, e.getMessage(), is(ERROR_MOVIMIENTO_NO_VALIDO));
			assertThat(POSICION_NO_ESPERADA, peonBlancoH.getPosicion(), is(new Posicion(2, 'h')));
		}
	}
	
	@Test
	public void toStringTest() {
		Peon peon = new Peon();
		assertThat(CADENA_NO_ESPERADA, peon.toString(), is("fila=7, columna=d, color=NEGRO"));
	}

}
