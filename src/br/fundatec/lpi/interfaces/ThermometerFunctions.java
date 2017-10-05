package br.fundatec.lpi.interfaces;

/**
 * Interface para ser usada em aparelhos que possuam um termometro
 * 
 * @author JP Martins, Ti11
 *
 */
public interface ThermometerFunctions {

	/**
	 * Gera a temperatura
	 */
	public void takeTemperature();

	/**
	 * Retorna a temperatura gerada anteriormente
	 * 
	 * @return temperatura gerada anteriormente
	 */
	public int getTemperature();
}
