package br.fundatec.lpi.interfaces;

/**
 * Interface generica para padronizar os comandos do banco de dados
 * 
 * @author JP Martins, TI11
 *
 * @param <InsertType>
 *            Tipo do objeto para realizar o comando "insert"
 * @param <SelectType>
 *            Tipo do retorno do comando "select"
 */
public interface DataBaseComandsDao<InsertType, SelectType> {

	/**
	 * Metodo para realizar o comando "insert"
	 * 
	 * @param insertObject
	 *            Objeto usado para realizar o "insert"
	 */
	public void insert(InsertType insertObject);

	/**
	 * Metodo para realizar o comando "select"
	 * @return Dados da tabela especificada
	 * 
	 */
	public SelectType select();
}
