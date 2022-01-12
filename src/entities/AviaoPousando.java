package entities;

import java.util.Date;

public class AviaoPousando {
	Pista p;
	public boolean isFlying2 = true;
	public Date data_pouso;
	public Date data_nascimento;
	public String nome;

	public AviaoPousando(boolean isFlying2, Date data_pouso, Date data_nascimento, String nome) {
		this.isFlying2 = isFlying2;
		this.data_pouso = data_pouso;
		this.data_nascimento = data_nascimento;
		this.nome = nome;
		this.p = p;
	}

	public AviaoPousando() {

	}

	public boolean isFlying() {
		return isFlying2;
	}

	public Date getData_pouso() {
		return data_pouso;
	}

	public void setFlying(boolean isFlying2) {
		this.isFlying2 = isFlying2;
	}

	public void setData_pouso(Date data_pouso) {
		this.data_pouso = data_pouso;
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
	
	public void run() {
		
	}
	
	public void Pousar() {
		while (true) {
			synchronized (p) {
				if (p.isFree) {
					System.out.println("Esperando 5 segundos");
					System.out.println("Pista em uso para pouso por " + Thread.currentThread().getName());
					// for (int s = 1; s <= 5; s++) {
					try {
						p.isFree = false;
						p.notifyAll();
						Thread.sleep(7000);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
					// }
					System.out.println("Passaram 7 segundos");
                    p.isFree = true;
                    isFlying2 = false;
                    System.out.println(Thread.currentThread().getName() + " decolou às: " + new Date(System.currentTimeMillis()));
				} else {
					System.out.println("Acidente ocorreu no pouso do avião: " + Thread.currentThread().getName());
					notifyAll();
					return;
				}
			}
		}
	}

}
