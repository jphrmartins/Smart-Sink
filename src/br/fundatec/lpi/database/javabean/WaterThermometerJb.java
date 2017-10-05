package br.fundatec.lpi.database.javabean;

import java.util.Calendar;

/**
 * Classe que representa a tabela "waterTermometer" do banco de dados no java, possui apenas
 * Getters and Setters
 * 
 * @author JP Martins
 *
 */
public class WaterThermometerJb {

	private long id;
	private int temperature;
	private boolean warm;
	private Calendar change_time;

	public boolean isWarm() {
		return warm;
	}

	public void setWarm(boolean warm) {
		this.warm = warm;
	}

	public Calendar getChange_time() {
		return change_time;
	}

	public void setChange_time(Calendar change_time) {
		this.change_time = change_time;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

}
