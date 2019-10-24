package org.iesalandalus.programacion.peonajedrez;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

import org.iesalandalus.programacion.peonajedrez.Color;
import org.junit.Test;

public class ColorTest {

	@Test
	public void cuandoSeAsignaValorAUnColorSuNombreEsCorrecto() {
		//Pasará el test si se han nombrado correctamente todos los colores
		Color color;
		color = Color.BLANCO;
		assertThat(color, is(Color.BLANCO));
		color = Color.NEGRO;
		assertThat(color, is(Color.NEGRO));
	}

}
