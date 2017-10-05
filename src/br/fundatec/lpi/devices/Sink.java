package br.fundatec.lpi.devices;

import java.util.Random;

import br.fundatec.lpi.interfaces.Balance;

/**
 * Classe que representa uma pia com uma balanca, possui a interface Balance
 * @author Jp Martins, Ti11
 *
 */
public class Sink implements Balance {

	private int weight;

	/**
	 * Construtor da classe que realiza o Metodo "measureWeight"
	 */
	public Sink() {
		measureWeight();
	}

	@Override
	public int getWeight() {
		return weight;
	}

	@Override
	public void measureWeight() {
		Random randomWeight = new Random();
		this.weight = randomWeight.nextInt(2000);
	}

}
