package org.iesalandalus.programacion.peonajedrez;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.utilidades.Entrada;

public class MainApp {
	private static Peon peon;

	private static void mostrarPeon() {
		System.out.print(peon.toString());
	}

	private static void mostrarMenu() {
		System.out.println("Menú:");
		System.out.println("1. Crear peón por defecto.");
		System.out.println("2. Crear peón de un color.");
		System.out.println("3. Crear peón de un color y columna inicial.");
		System.out.println("4. Mover.");
		System.out.println("5. Salir.");
		ejecutarOpcion(elegirOpcion());
	}

	private static int elegirOpcion() {
		int eleccion;
		do {
			System.out.print("Elige una opción:");
			eleccion = Entrada.entero();
		} while (eleccion > 5 || eleccion < 1);
		return eleccion;

	}

	private static Color elegirColor() {
		int color;
		do {
			System.out.println("");
			System.out.println("1. Negro.");
			System.out.println("2. Blanco.");
			System.out.print("Elige un color:");
			color = Entrada.entero();
		} while (color < 1 || color > 2);

		switch (color) {
		case 1:
			return Color.NEGRO;
		case 2:
			return Color.BLANCO;
		}
		return null;
	}

	private static char elegirColumnaInicial() {
		char columna;
		do {
			System.out.print("Elige una columna inicial (de 'a' a 'h'):");
			columna = Entrada.caracter();
		} while (columna != 'a' && columna != 'b' && columna != 'c' && columna != 'd' && columna != 'e'
				&& columna != 'f' && columna != 'g' && columna != 'h');
		return columna;

	}

	private static void mostrarMenuMovimientos() {
		System.out.println("");
		System.out.println("Movimientos");
		System.out.println("1. Avanzar un paso.");
		System.out.println("2. Avanzar dos pasos.");
		System.out.println("3. Avanzar hacia la izquierda.");
		System.out.println("4. Avanzar hacia la derecha.");

	}

	private static int elegirMovimiento() {
		int movimiento;

		do {
			System.out.print("Elige un movimiento:");
			movimiento = Entrada.entero();
		} while (movimiento < 1 || movimiento > 4);
		return movimiento;

	}

	private static void realizarMovimiento(int movimiento) {
		switch (movimiento) {
		case 1:
			try {
				peon.mover(1);
			} catch (OperationNotSupportedException e) {
				System.out.println("");
				System.out.println(e.getMessage());
				System.out.println("Elige otro movimiento que sea válido.");
				mover();

			}
			break;

		case 2:
			try {
				peon.mover(2);
			} catch (OperationNotSupportedException e) {
				System.out.println("");
				System.out.println(e.getMessage());
				System.out.println("Elige otro movimiento que sea válido.");
				mover();

			}
			break;
		case 3:
			try {
				peon.mover(Direccion.IZQUIERDA);
			} catch (OperationNotSupportedException e) {
				System.out.println("");
				System.out.println(e.getMessage());
				System.out.println("Elige otro movimiento que sea válido.");
				mover();

			}
			break;
		case 4:
			try {
				peon.mover(Direccion.DERECHA);
			} catch (OperationNotSupportedException e) {
				System.out.println("");
				System.out.println(e.getMessage());
				System.out.println("Elige otro movimiento que sea válido.");
				mover();

			}
			break;
		}
	}

	private static void crearPeonDefecto() {

		peon = new Peon();
		System.out.println("");
		System.out.println("Se ha creado un peón.");
		mostrarPeon();
		System.out.println("");
		mostrarMenu();
	}

	private static void crearPeonColor() {

		peon = new Peon(elegirColor());
		System.out.println("");
		System.out.println("Se ha creado un peón.");
		mostrarPeon();
		System.out.println("");
		mostrarMenu();
	}

	private static void crearPeonColorColumna() {
		peon = new Peon(elegirColor(), elegirColumnaInicial());
		System.out.println("");
		System.out.println("Se ha creado un peón.");
		mostrarPeon();
		System.out.println("");
		mostrarMenu();
	}

	private static void mover() {

		if (peon == null) {
			System.out.println("");
			System.out.println("Primero crea un peón.");
			System.out.println("");
			mostrarMenu();
		}
		mostrarMenuMovimientos();
		realizarMovimiento(elegirMovimiento());
		System.out.println("Movimiento realizado.");
		System.out.println("");
		mostrarPeon();
		System.out.println("");

	}

	private static void ejecutarOpcion(int eleccion) {

		switch (eleccion) {
		case 1:
			crearPeonDefecto();
			break;

		case 2:
			crearPeonColor();
			break;
		case 3:
			crearPeonColorColumna();
			break;
		case 4:
			mover();
			mostrarMenu();
			break;
		case 5:
			System.out.print("");
			break;
		}

	}

	public static void main(String[] args) {
		System.out.println("Programa para aprender a colocar y mover un peón en el tablero de ajedrez");
		System.out.println("-------------------------------------------------------------------------");
		mostrarMenu();

		System.out.println("Hasta luego Lucas!!!!");
	}

}
