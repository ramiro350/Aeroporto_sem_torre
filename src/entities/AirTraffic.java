package entities;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class AirTraffic {

	Pista p;

	public void Start() {
		Thread t1[] = new Thread[18];
		Thread t2[] = new Thread[18];
		for (int i = 0; i < 18; i++) {
			boolean rand = ThreadLocalRandom.current().nextBoolean();
			if (rand) {
				// for(int contar=0;contar<9;contar++) {
				t1[i] = new Thread(new Runnable1(p));
				t1[i].setName("Aviao-P" + i);
				t1[i].start();
				// }

			} else if (!rand) {
				// for(int contte=0;contte<9;contte++) {

				t2[i] = new Thread(new Runnable2(p));
				t2[i].setName("Aviao-D" + i);
				t2[i].start();

				// }
			}
		}

	}
}

class Runnable1 implements Runnable {
	Pista p;

	public Runnable1(Pista p) {
		this.p = p;
	}

	public synchronized void run() {
		AviaoDecolando avd = new AviaoDecolando();
		avd.setData_nascimento(new Date(System.currentTimeMillis()));
		// Thread p = new Thread(avd);
		System.out.println(Thread.currentThread().getName() + " criado no ar");
		// System.out.println("Esperando 7 segundos");

		try {

			Thread.sleep(7000);
			notifyAll();
			System.out.println("Passaram 7 segundos da criação do avião: " + Thread.currentThread().getName());
			if (this.p.PistaTaxiamento.size() >= 3) {
                System.out.println("Acidente na pista");
				return;
			} else if (this.p.PistaTaxiamento.size() < 3 && this.p.PistaTaxiamento.size() >= 0) {
				p.PistaTaxiamento.add(avd);
				while (!avd.isFlying1) {
					avd.run();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class Runnable2 implements Runnable {
	Pista p;

	public Runnable2(Pista p) {
		this.p = p;
	}

	public synchronized void run() {
		AviaoPousando avt = new AviaoPousando();
		avt.setData_nascimento(new Date(System.currentTimeMillis()));

		System.out.println(Thread.currentThread().getName() + " criado na terra");
		// System.out.println("Esperando 7 segundos");
		try {

			Thread.sleep(7000);
			notifyAll();
			System.out.println("Passaram 7 segundos da criação do avião: " + Thread.currentThread().getName());
			if (this.p.PistaAerea.size() >= 3) {
                System.out.println("Acidente no ar");
				return;
			} else if (this.p.PistaAerea.size() < 3 && this.p.PistaAerea.size() >= 0) {
				p.PistaAerea.add(avt);
				while (avt.isFlying2) {
					avt.run();
				}
			}

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}