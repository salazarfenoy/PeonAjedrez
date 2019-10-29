package org.iesalandalus.programacion.peonajedrez;

import javax.naming.OperationNotSupportedException;

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
	
	public void mover(Direccion direccion) throws OperationNotSupportedException {
		
		switch (direccion){
		case IZQUIERDA:
			try {
				setPosicion(new Posicion(posicion.getFila(), (char)(posicion.getColumna() - 1)));
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido.");
			}
			break;
		case DERECHA:
			try {
				setPosicion(new Posicion(posicion.getFila(), (char)(posicion.getColumna() + 1)));
			} catch (IllegalArgumentException e) {
				throw new OperationNotSupportedException("ERROR: Movimiento no válido.");
			}
			break;
		}
		
	}
	
	public void mover (int movFila) throws OperationNotSupportedException {
		switch (movFila) {
		case 1:
				setPosicion(new Posicion(posicion.getFila() + movFila, (char)(posicion.getColumna())));
				
			break;
		case 2:
		if (color== Color.BLANCO && posicion.getFila()== 2) {
			setPosicion(new Posicion(posicion.getFila() + movFila, (char)(posicion.getColumna())));
			
		} else {
			throw new OperationNotSupportedException("ERROR: El peón sólo se puede mover 2 pasos cuando se encuentra en la casilla inicial.");
		}
		
	break;
	default:
		throw new OperationNotSupportedException("ERROR: El peón sólo se puede mover 1 o 2 pasos.");
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Peon other = (Peon) obj;
		if (color != other.color)
			return false;
		if (posicion == null) {
			if (other.posicion != null)
				return false;
		} else if (!posicion.equals(other.posicion))
			return false;
		return true;
	}
	
	
}
