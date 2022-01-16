package application;

import java.util.Scanner;

import entities.AirTraffic;


public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// ArrayList<AviaoPousando> avioesvoando = new ArrayList<AviaoPousando>(3);

		AirTraffic airtraffic = new AirTraffic();
		airtraffic.Start();

	}
}
