package controller;

import java.util.Random;

public class Sapo implements Runnable {
	private static final Random rd = new Random ();
	private final int id;
	private final int saltoMax;
	private final int maxDist;
	private double distTot = 0;
	private boolean arrive= false;
	private final long startTime;
	private final double[] podio;
	
	public Sapo (int id, int saltoMax, int maxDist, long startTime, double[] podio) {
		this.startTime = startTime;
		this.id = id;
		this.saltoMax =saltoMax;
		this.maxDist = maxDist;
		this.podio = podio;
	}

	@Override
	public void run() {
		while (!arrive) {
			double salto = rd.nextDouble() * saltoMax;
			distTot += salto;
			 System.out.printf("O Sapo %d deu um salto de %.2f metros. Distância percorrida: %.2f metros.%n", id, salto, distTot);
		
			 if(distTot >= maxDist) {
				 long finalTime = System.nanoTime();
				 double time= (finalTime - startTime)/1_000_000.0;
				 arrive =true;
				 podio[id-1] = time;
				 System.out.printf("Sapo %d chegou na meta com %.2f metros em um tempo total de  %.2f milisegundos!%n", id, distTot, time);
				 
			 }
		
		}
		
	}

}
