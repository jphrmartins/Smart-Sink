package br.fundatec.lpi.interfaces;

/**
 * Interface para ser usada em aparalhes que possuem um aquecedor de agua
 * @author JP Martins, Ti11
 *
 */
public interface WarmWaterFunctions {

	/**
	 * Metodo que informa se a agua esta sendo aquecida
	 * @return Aparelho aquecendo agua 
	 */
	public String warmWater();
	/**
	 * Metodo que retorna o status do aquecedor de água
	 * @return
	 * Status do aquecedor
	 */
	public boolean getStatus();
}
