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

	public void run() {

	}

	public void Decolar() {
		while (true) {
			synchronized (p) {
				if (p.isFree) {
					System.out.println("Esperando 5 segundos");
					System.out.println("Pista em uso por " + Thread.currentThread().getName());
					// for (int s = 1; s <= 5; s++) {
					try {
						p.isFree = false;
						p.notifyAll();
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
					// }
					System.out.println("Passaram 5 segundos");
                    p.isFree = true;
                    isFlying1 = true;
                    System.out.println(Thread.currentThread().getName() + " decolou às: " + new Date(System.currentTimeMillis()));
				} else {
					System.out.println("Acidente ocorreu na decolagem do avião: " + Thread.currentThread().getName());
					notifyAll();
					return;
				}
			}
		}
	}

}
