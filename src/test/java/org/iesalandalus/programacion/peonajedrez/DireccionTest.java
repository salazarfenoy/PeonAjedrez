package org.iesalandalus.programacion.peonajedrez;

import static org.junit.Assert.*;

import org.iesalandalus.programacion.peonajedrez.Direccion;

import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

public class DireccionTest {

	@Test
	public void cuandoSeAsignaValorAUnaDireccionSuNombreEsCorrecto() {
		//Pasará el test si hemos nombrado bien todas las direcciones
		Direccion direccion;
		direccion = Direccion.IZQUIERDA;
		assertThat(direccion, is(Direccion.IZQUIERDA));
		direccion = Direccion.DERECHA;
		assertThat(direccion, is(Direccion.DERECHA));
	}

}
