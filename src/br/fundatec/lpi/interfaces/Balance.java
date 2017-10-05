package br.fundatec.lpi.interfaces;

/**
 * Interface para ser usada em aparelhos que possuem balanca
 * 
 * @author JP Martins, Ti11
 *
 */
public interface Balance {

	/**
	 * Metodo que mede o peso
	 */
	public void measureWeight();

	/**
	 * Metodo que retoran o peso
	 * 
	 * @return Peso da balanca
	 */
	public int getWeight();
}
