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

	public synchronized void run() {
		while (!p.isFree) {
			try {
				System.out.println(Thread.currentThread().getName() + " esperando a pista ficar livre para");
				wait();
				notifyAll();
			} catch (InterruptedException e) {

			}

		}if(p.avioesdecolaram.size()<9 && p.avioespousaram.size()<9) {
			System.out.println("Todas as operações foram concluídas");
			System.exit(0);
		}
		noAr();
	}

	public void noAr() {
		while (true) {
			synchronized (p) {
				try {
					if (p.isFree) {
						Pousar();
					} else {
						Thread.sleep(30000);

						if (p.isFree) {
							Pousar();
						} else {
							System.out.println(
									"Limite de tempo do avião" + Thread.currentThread().getName() + "esgotado");
							notifyAll();
							return;
						}
					}
				} catch (InterruptedException e) {

				}
			}
		}
	}

	public void Pousar() {
		while (true) {
			synchronized (p) {
				// for (int s = 1; s <= 5; s++) {
				try {
					if (p.isFree) {
						System.out.println("Esperando 5 segundos");
						System.out.println("Pista em uso para pouso por " + Thread.currentThread().getName());
						p.isFree = false;
						p.notifyAll();
						Thread.sleep(1000);
						System.out.println("Passaram 7 segundos");
						isFlying2 = false;
						this.setData_pouso(new Date(System.currentTimeMillis()));
						System.out.println(Thread.currentThread().getName() + " pousou às: " + this.getData_pouso());
						System.out.println("Aguardando pista ficar livre");
						Thread.sleep(5000);
						p.isFree = true;
						p.notifyAll();
						for (int i = 0; i <= p.PistaAerea.size(); i++) {
							if (p.PistaAerea.get(i) == this) {
								p.PistaAerea.remove(this);
								notifyAll();
								System.out.println(Thread.currentThread().getName() + " removido da pista aerea");
							}
						}
					} else {
						System.out.println("Acidente ocorreu no pouso do avião: " + Thread.currentThread().getName());
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
