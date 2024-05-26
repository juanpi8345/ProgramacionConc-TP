package test;

import java.util.Arrays;

import util.Funciones;

public class TestAlgoritmos implements Runnable {

	public TestAlgoritmos(long inicio, long fin) {
		super();
		this.inicio = inicio;
		this.fin = fin;
	}

	public static int array[];
	private static long tiempoInicio;

	private long inicio;
	private long fin;

	@Override
	public void run() {
		
		Funciones.quickSort(Arrays.copyOf(array, array.length), (int) inicio, (int) fin);
	}

	public static void main(String[] args) {
		//Declaro la variable para manejar el tamaño del array
		long tamanioArr = 0;
		//Declaro una variable para concatenar en cada Sysout.
		String posiciones = "";
		//Hago un for para no repetir codigo y poder ejecutar 4 casos de uso
		for(int i = 0; i<4; i++) {
			switch(i) {
				//Primer caso de uso, un array con mil posiciones
				case 0:
					tamanioArr = 1000;
					posiciones = "mil posiciones";
					break;
				//Segundo caso de uso, un array con diez mil posiciones
				case 1:
					tamanioArr = 10000;
					posiciones = "diez mil posiciones";
					break;
				//Tercer caso de uso, un array con cien mil posiciones
				case 2:
					tamanioArr = 100000;
					posiciones = "cien mil posiciones";
					break;
				//Cuarto caso de uso, un array con un millon de posiciones
				default:
					tamanioArr = 1000000;
					posiciones = "un millon posiciones";
					break;
			}
			
			//Genero un array aleatorio, con el tamanio que corresponda a la iteracion
			//Las posiciones siempre se llenaran de manera aleatoria entre 0 a 10000
			array = Funciones.generarArrayAleatorio((int)tamanioArr, 0, 10000);

			//Concateno la cantidad de posiciones del algoritmo, nuevamente correspondiente a la iteracion
			System.out.println("Algoritmo QuickSort de ".concat(posiciones).concat(", de forma secuencial:"));

			//Obtengo el tiempo de inicio
			tiempoInicio = System.nanoTime();
			
			/*Llamo a la funcion quicksort, donde le envio una copia del array con su tamaño
			 	,la posicion de inicio y fin.
			 */
			Funciones.quickSort(Arrays.copyOf(array, array.length), 0, array.length - 1);
			// Indico cuanto tardo
			System.out.println("Tardo: " + (System.nanoTime() - tiempoInicio) + "ns");
	
			System.out.println("");
			
			//Concateno la cantidad de posiciones del algoritmo, nuevamente correspondiente a la iteracion
			//Esta vez del algoritmo concurrente
			System.out.println("Algoritmo QuickSort de ".concat(posiciones).concat(", de forma concurrente:"));

			//En este caso, voy a utilizar 6 hilos
			Thread[] hilos = new Thread[6];
			
			/*Llamo a una funcion para ejecutar el algoritmo de forma concurrente
			  Le envio los hilos, la cantidad de hilos, y el tamanio del array
			  Dentro de esta funcion se calcula el tiempo que tardo
			 */
			Funciones.ejecutarAlgoritmoConcurrente(hilos, 6, array.length);
			System.out.println("");
		}	
	}
}
