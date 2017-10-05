package br.fundatec.lpi.database.javabean;

/**
 * Classe que representa a tabela "user_priority" do banco de dados no java, possui apenas
 * Getters and Setters
 * 
 * @author JP Martins
 *
 */
public class UserJb {

	private long id;
	private int max_weight;
	private int ideal_temperature;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMax_weight() {
		return max_weight;
	}

	public void setMax_wheight(int max_weight) {
		this.max_weight = max_weight;
	}

	public int getIdeal_temperature() {
		return ideal_temperature;
	}

	public void setIdeal_temperature(int ideal_temperature) {
		this.ideal_temperature = ideal_temperature;
	}

}
