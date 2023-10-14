package com.dfleper.BikeParkingSemaphore;

public class App {
	public static void main(String[] args) {
		int numExcursionists = 10;
		int maxBikes = 3;
		int maxHelmets = 3;
		int durationExcursion = 5000; // milliseconds

		System.out.println("El número total de Excursionistas es de " + numExcursionists);
		System.out.println("El número total de Bicicletas es de " + maxBikes);
		System.out.println("El número total de Cascos es de " + maxHelmets);
		System.out.println("La duración máxima de la Excursion es de " + durationExcursion + " milisegundos.");
		System.out.println("");
		System.out.println("");

		BikeParking bp = new BikeParking(maxBikes, maxHelmets, durationExcursion);

		for (int i = 1; i <= numExcursionists; i++) {
			Excursionist exc = new Excursionist("Agente Smith " + i, bp);
			exc.start();
		}
	}
}
