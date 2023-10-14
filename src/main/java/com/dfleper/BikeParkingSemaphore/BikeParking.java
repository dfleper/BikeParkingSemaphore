package com.dfleper.BikeParkingSemaphore;

import java.util.concurrent.Semaphore;

public class BikeParking {
	private int MaxBikes;
	private int MaxHelmets;
	private int DurationExcursion;

	private Semaphore smBikes;
	private Semaphore smHelmets;

	private Semaphore smMutexBikes;
	private Semaphore smMutexHelmets;

	public BikeParking(int maxBikes, int maxHelmets, int durationExcursion) {
		this.MaxBikes = maxBikes;
		this.MaxHelmets = maxHelmets;
		this.DurationExcursion = durationExcursion;

		this.smBikes = new Semaphore(maxBikes);
		this.smHelmets = new Semaphore(maxHelmets, true);

		this.smMutexBikes = new Semaphore(1);
		this.smMutexHelmets = new Semaphore(1, true);

	}

	public void getHelmet(String name) {

		try {
			System.out.println("El Excursionista " + name + " quiere coger un casco. (Cascos disponibles = "
					+ this.MaxHelmets + ")");
			this.smHelmets.acquire();
			this.smMutexHelmets.acquire();
			this.MaxHelmets--;
			this.smMutexHelmets.release();

			System.out.println("El Excursionista " + name + " ha cogido un casco.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getBike(String name) {

		try {
			System.out.println(
					"El Excursionista " + name + " quiere coger una Bici. (Bicis disponibles = " + this.MaxBikes + ")");
			this.smBikes.acquire();
			this.smMutexBikes.acquire();
			this.MaxBikes--;
			this.smMutexBikes.release();

			System.out.println("El Excursionista " + name + " ha cogido una Bici.");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void goExcursion(String name) {

		try {
			System.out.println("El Excursionista " + name + " está de Excursión. Tardará unos " + this.DurationExcursion
					+ " milisegundos.");
			Thread.sleep(DurationExcursion);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deliverHelmet(String name) {

		try {
			this.smMutexHelmets.acquire();
			this.MaxHelmets++;
			this.smMutexHelmets.release();

			System.out.println("El Excursionista " + name + " ha devuelto el casco. (Cascos disponibles = "
					+ this.MaxHelmets + ")");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.smHelmets.release();
		}
	}

	public void deliverBike(String name) {

		try {
			this.smMutexBikes.acquire();
			this.MaxBikes++;
			this.smMutexBikes.release();
			;

			System.out.println(
					"El Excursionista " + name + " ha devuelto la Bici. (Bicis disponibles = " + this.MaxBikes + ")");

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			this.smBikes.release();
		}
	}
}
