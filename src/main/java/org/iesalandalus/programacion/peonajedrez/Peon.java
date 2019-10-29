package org.iesalandalus.programacion.peonajedrez;

public class Peon {
	private Color color;
	private Posicion posicion;

	public Peon() {
		this.color = Color.NEGRO;
		this.posicion = new Posicion(7, 'd');
	}

	public Peon(Color color) {
		setColor(color);

		if (color == Color.BLANCO) {
			posicion = new Posicion(2, 'd');
		} else if (color == Color.NEGRO) {
			posicion = new Posicion(7, 'd');
		}
	}

	public Peon(Color color, char columna) {
		setColor(color);

		if (color == Color.BLANCO) {
			posicion = new Posicion(2, columna);
		} else if (color == Color.NEGRO) {
			posicion = new Posicion(7, columna);
		}
	}

	public Color getColor() {
		return color;
	}

	private void setColor(Color color) {
		if (color == null) {
			throw new NullPointerException("ERROR: No se puede asignar un color nulo.");
		}
		if (color == Color.BLANCO || color == Color.NEGRO) {
			this.color = color;
		}
	}

	public Posicion getPosicion() {
		return posicion;
	}

	private void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
}
