package com.dfleper.BikeParkingSemaphore;

public class Excursionist extends Thread {
	private String Name;
	private BikeParking BP;

	public Excursionist(String name, BikeParking bp) {
		this.Name = name;
		this.BP = bp;
	}

	@Override
	public void run() {
		this.BP.getHelmet(this.Name);
		this.BP.getBike(this.Name);
		this.BP.goExcursion(this.Name);
		this.BP.deliverHelmet(this.Name);
		this.BP.deliverBike(this.Name);
	}
}
