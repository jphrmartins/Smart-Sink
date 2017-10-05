package br.fundatec.lpi.devices;

/**
 * TAbela que representa um usuario, usada para definir a temperatura ideal e o
 * peso maximo da pia
 * 
 * @author JP Martins, Ti11
 *
 */
public class User {

	private int maxWeight;
	private int idealTemperature;

	/**
	 * Construtor da classe, Torna os atributos "maxWeight" e "idealTemperature"
	 * obrigatorios
	 * 
	 * @param maxWeight
	 *            Peso maximo para ser informado
	 * @param idealTemperature
	 *            Temperatura ideal para ser informada
	 */
	public User(int maxWeight, int idealTemperature) {
		this.maxWeight = maxWeight;
		this.idealTemperature = idealTemperature;
	}

	/**
	 * Retorna o valor do peso maximo desejado
	 * 
	 * @return Peso maximo
	 */
	public int getMaxWeight() {
		return maxWeight;
	}

	/**
	 * Retorna a temperatura ideal para o usuario
	 * 
	 * @return temperatura ideal
	 */
	public int getIdealTemperature() {
		return idealTemperature;
	}

}
