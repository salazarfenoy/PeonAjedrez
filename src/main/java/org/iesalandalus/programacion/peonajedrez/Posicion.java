package org.iesalandalus.programacion.peonajedrez;

public class Posicion {

	private int fila;
	private char columna;
	
	public Posicion(int fila, char columna) {
		setFila(fila);
		setColumna(columna);
	}

	public Posicion(Posicion posicion) {
		this.fila=posicion.fila;
		this.columna=posicion.columna;
	}
	public int getFila() {
		return fila;
	}

	private void setFila(int fila) {

		if (fila > 0 || fila < 9) {
			this.fila = fila;
		} else {
			throw new IllegalArgumentException("ERROR: Fila no válida.");

		}
	}

	public char getColumna() {
		return columna;
	}

	private void setColumna(char columna) {
		if (columna == 'a' || columna == 'b' || columna == 'c' || columna == 'd' || columna == 'e' || columna == 'f'
				|| columna == 'g' || columna == 'h') {
			this.columna = columna;
		} else {
			throw new IllegalArgumentException("ERROR: Columna no válida.");
		}
	}

}
