package entities;

import java.util.ArrayList;
import java.util.List;

public class Pista {
	boolean isFree = true;
	public List<AviaoDecolando> PistaTaxiamento = new ArrayList<AviaoDecolando>();
	public List<AviaoPousando> PistaAerea = new ArrayList<AviaoPousando>();
	public List<AviaoDecolando> avioesdecolaram = new ArrayList<AviaoDecolando>();
	public List<AviaoPousando> avioespousaram = new ArrayList<AviaoPousando>();
}
