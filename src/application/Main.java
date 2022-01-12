package application;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


import entities.AviaoDecolando;
import entities.AviaoPousando;
import entities.Pista;



class Runnable1 implements Runnable {
	public void run() {
		for (int i = 0; i < 9; i++) {
			AviaoPousando aviaopousando = new AviaoPousando();
			aviaopousando.setData_nascimento(new Date(System.currentTimeMillis()));
			System.out.println("avião " + i + " criado no ar");
			System.out.println("Esperando 7 segundos");
			for (int s = 1; s <= 7; s++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Passaram " + s + " segundos");
			}
		}
	}
}

class Runnable2 implements Runnable {
	ArrayList<AviaoDecolando> PistaTaxiamento = new ArrayList<AviaoDecolando>(3);
	public void run() {
		for (int j = 0; j < 9; j++) {
			AviaoDecolando aviaodecolando = new AviaoDecolando();
			aviaodecolando.setData_nascimento(new Date(System.currentTimeMillis()));
			PistaTaxiamento.add(aviaodecolando);
			System.out.println("avião " + j + " criado na terra");
			System.out.println("Esperando 7 segundos");
			for (int s = 1; s <= 7; s++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Passaram " + s + " segundos");
			}
		}
	}
}

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		ArrayList<AviaoPousando> avioesvoando = new ArrayList<AviaoPousando>(3);
		Pista p;

		Thread[] t1 = new Thread[8];
		Thread[] t2 = new Thread[8];
		for (int i = 0; i < 18; i++) {
			boolean rand = ThreadLocalRandom.current().nextBoolean();
			if (rand) {
				for(int contar=0;contar<9;contar++) {
					t1[contar] = new Thread(new Runnable1()); 					
					t1[contar].start();
				}
			    
			} else {
				for(int contte=0;contte<9;contte++) {
					t2[contte] = new Thread(new Runnable2());
					t2[contte].start();
				}
			}
		}
		
	}
}
