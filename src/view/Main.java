package view;

import controller.Sapo;

public class Main {

	public static void main(String[] args) {
		final int quantSapo = 5;
		final int saltoMax = 10;
		final int distMax = 50;
		final long startTime = System.nanoTime();
			
	Thread[] threads = new Thread [quantSapo];
	double[] podio = new double[quantSapo];
	Sapo[] sapos = new Sapo[quantSapo];
	
	System.out.println("Começa a corrida de Sapos!");
	for (int i = 0; i < quantSapo; i++) {
		sapos[i] = new Sapo(i+1, saltoMax, distMax, startTime, podio);
		threads[i] = new Thread(sapos[i]);
		threads[i].start();
	}
	 for (Thread thread : threads) {
         try {
             thread.join();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
     }
	 
	Integer[] index = new Integer [quantSapo];
	for (int i = 0; i< quantSapo; i++) {
		index[i] = i;
	}
	
	for (int i = 0; i < index.length - 1; i++) {
        for (int j = 0; j < index.length - 1 - i; j++) {
            if (podio[index[j]] > podio[index[j + 1]]) {
                int temp = index[j];
                index[j] = index[j + 1];
                index[j + 1] = temp;
            }
        }
    }
	
	 System.out.println("\nClassificação final da corrida:");
	 System.out.printf("%-10s %-10s %-10s%n", "Colocação", "Sapo", "Tempo(ms)");
     for (int i = 0; i < quantSapo; i++) {
         int sapoIndex = index[i] + 1;
         double tempo = podio[index[i]];
         System.out.printf("%-10d %-10d %-15.2f%n", i + 1, sapoIndex, tempo);
     }
	
	}

}
