package util;

import java.util.Arrays;
import java.util.Random;

import test.TestAlgoritmos;

public class Funciones {

	public static int[] generarArrayAleatorio(int n, int min, int max) {
		// Declaración del array
		int[] arr = new int[n];

		// Generación de números aleatorios
		Random random = new Random();
		for (int i = 0; i < n; i++) {
			arr[i] = random.nextInt(max - min + 1) + min;
		}

		return arr;
	}

	//Recorre y muestra el array
	public static void mostrarArray(int[] arr) {
		System.out.println("\n-----------------------:\n");
		for (int num : arr) {
			System.out.print(num + " - ");
		}
	}

	// Algoritmo a utilizar
	public static void quickSort(int[] arr, int low, int high) {
		//Si el indice mas chico es menor que el mas alto
		if (low < high) {
			//Divide el array y obtiene el indice del pivote
			int pi = partition(arr, low, high);
			//Recursividad para ordenar el subarray de la izquierda del pivote
			quickSort(arr, low, pi - 1);
			//Recursividad para ordenar el subarray de la la derecha del pivote
			quickSort(arr, pi + 1, high);
		}
	}

	private static int partition(int[] arr, int low, int high) {
		//Ultimo elemento como pivote
		int pivot = arr[high];
		//Indice del elemento mas chico
		int i = low - 1;
		//Itera desde el cmas chico al alto
		for (int j = low; j < high; j++) {
			//Si el actual es mas chico que el pivote
			if (arr[j] < pivot) {
				//Se incrementa el indice del elemento mas chico
				i++;
				//Guarda el elemento de la pos i (Aux)
				int temp = arr[i];
				//Pone el elemento menor del pivote en la pos i
				arr[i] = arr[j];
				//Pone el elemento guardado en la pos j
				arr[j] = temp;
			}
		}
		//Guarda el elemento en la pos i+1
		int temp = arr[i + 1];
		//Pone al pivote en su posicion
		arr[i + 1] = arr[high];
		//Pone al elemento guardado en la ultima pos
		arr[high] = temp;
		//Retorna indice del pivote
		return i + 1;
	}

	public static void ejecutarAlgoritmoConcurrente(int cantidadHilos, int tamArr) {
		
		Thread[] hilos = new Thread[cantidadHilos];
		
		//EL inicio arranca en 0
		long inicio = 0;
		//El fin arranca desde la division entre el tamanio del array y la cantidad de hilos
		long fin = tamArr / cantidadHilos;

		//Se recorren la cantidad de hilos
		for (int i = 0; i < cantidadHilos; i++) {
			//Instancio cada hilo con su correspondiente inicio y fin
			hilos[i] = new Thread(new TestAlgoritmos(inicio, fin));
			//El fin ahora es el inicio
			inicio = fin;
			//Se agrega a fin el inicio
			fin += inicio;

			//Si fin supera al tamanio de array se le asigna la ultima posicion del array
			if (fin > tamArr)
				fin = tamArr - 1;
		}
		long tiempoInicio = System.nanoTime();
		//Se inician todos los hilos
		for (int i = 0; i < cantidadHilos; i++)
			hilos[i].start();
		//Se esperan todos los hilos
		for (int i = 0; i < cantidadHilos; i++) {
			try {
				hilos[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//Se imprime lo que tardaron
		System.out.println("Tardo: "+(System.nanoTime()-tiempoInicio) +"ns");
	}

}
