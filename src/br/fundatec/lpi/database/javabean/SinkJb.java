package br.fundatec.lpi.database.javabean;

import java.util.Calendar;

/**
 * Classe que representa a tabela "sink"  do banco de dados no java, possui apenas Getters and Setters
 * @author JP Martins
 *
 */
public class SinkJb {

	private long id;
	private double wheight;
	private Calendar changingTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getWheight() {
		return wheight;
	}

	public void setWheight(double wheight) {
		this.wheight = wheight;
	}

	public Calendar getChangingTime() {
		return changingTime;
	}

	public void setChangingTime(Calendar date, Calendar dateTime) {
		date.set(Calendar.HOUR_OF_DAY, dateTime.get(Calendar.HOUR_OF_DAY));
		date.set(Calendar.MINUTE, dateTime.get(Calendar.MINUTE));
		date.set(Calendar.SECOND, dateTime.get(Calendar.SECOND));
		this.changingTime = date;
	}

}
