package entities;

import java.util.Date;

public class AviaoDecolando implements Runnable {

	Pista p;
	public boolean isFlying1 = false;
	public Date data_nascimento;
	public Date data_decolagem;
	public String nome;

	public AviaoDecolando(boolean isFlying1, Date data_decolagem, Date data_nascimento, String nome) {
		this.isFlying1 = isFlying1;
		this.data_decolagem = data_decolagem;
		this.data_nascimento = data_nascimento;
		this.nome = nome;
		this.p = p;
	}

	public AviaoDecolando() {

	}

	public boolean isFlying() {
		return isFlying1;
	}

	public void setFlying(boolean isFlying1) {
		this.isFlying1 = isFlying1;
	}

	public Date getData_decolagem() {
		return data_decolagem;
	}

	public void setData_decolagem(Date data_decolagem) {
		this.data_decolagem = data_decolagem;
	}

	public Date getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public synchronized void run() {
		while (!p.isFree) {
			try {
				System.out.println(Thread.currentThread().getName() + " esperando a pista ficar livre para");
				wait();
				notifyAll();
			} catch (InterruptedException e) {

			}
		}
		if (p.avioesdecolaram.size() < 9 && p.avioespousaram.size() < 9) {
			System.out.println("Todas as operações foram concluídas");
			System.exit(0);
		}
		Decolar();
	}

	public void Decolar() {
		while (true) {
			synchronized (p) {
				// for (int s = 1; s <= 5; s++) {
				try {
					if (p.isFree) {
						System.out.println("Esperando 5 segundos");
						System.out.println("Pista em uso por " + Thread.currentThread().getName());
						p.isFree = false;
						p.notifyAll();
						Thread.sleep(5000);
						System.out.println("Passaram 5 segundos");
						isFlying1 = true;
						this.setData_decolagem(new Date(System.currentTimeMillis()));
						System.out
								.println(Thread.currentThread().getName() + " decolou às: " + this.getData_decolagem());
						System.out.println("Aguardando pista ficar livre");
						Thread.sleep(5000);
						p.isFree = true;
						p.notifyAll();
						for (int i = 0; i <= p.PistaTaxiamento.size(); i++) {
							if (p.PistaTaxiamento.get(i) == this) {
								p.PistaTaxiamento.remove(this);
								notifyAll();
								System.out
										.println(Thread.currentThread().getName() + " removido da pista de taxiamento");
							}
						}
					} else {
						System.out
								.println("Acidente ocorreu na decolagem do avião: " + Thread.currentThread().getName());
						notifyAll();
						return;
					}
				} catch (InterruptedException e) {
					// e.printStackTrace();
				}
				// }
			}
		}
	}

}