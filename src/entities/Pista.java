package entities;

import java.util.ArrayList;
import java.util.List;

public class Pista {
	boolean isFree = true;
	public List<AviaoDecolando> PistaTaxiamento = new ArrayList<AviaoDecolando>();
	public List<AviaoPousando> PistaAerea = new ArrayList<AviaoPousando>();
	public List<AviaoDecolando> avioesdecolaram = new ArrayList<AviaoDecolando>();
	public List<AviaoPousando> avioespousaram = new ArrayList<AviaoPousando>();
	
	public Pista(boolean isFree, List<AviaoDecolando> pistaTaxiamento, List<AviaoPousando> pistaAerea,
			List<AviaoDecolando> avioesdecolaram, List<AviaoPousando> avioespousaram) {
		
		this.isFree = isFree;
		this.PistaTaxiamento = pistaTaxiamento;
		this.PistaAerea = pistaAerea;
		this.avioesdecolaram = avioesdecolaram;
		this.avioespousaram = avioespousaram;
	}

	public Pista() {
		
	}
	
	

}
