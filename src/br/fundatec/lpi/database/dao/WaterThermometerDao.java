package br.fundatec.lpi.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.fundatec.lpi.database.javabean.WaterThermometerJb;
import br.fundatec.lpi.devices.WaterThermometer;

/**
 * Classe que realiza as interações com a tabela "water_thermometer" no banco de
 * dados
 * 
 * @author JP Martins, Ti11
 *
 */
public class WaterThermometerDao {

	private Connection con;

	/**
	 * Construtor da classe, estabelece uma conecao
	 * 
	 * @param con
	 */
	public WaterThermometerDao(Connection con) {
		super();
		this.con = con;
	}

	/**
	 * Metodo usado para criar a tebela
	 */
	public void create() {
		String sql = "create table water_thermometer( id serial primary key, temperature int, warm boolean, change_time timestamp);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo para realizar o comando "Insert"
	 * @param waterThermometer
	 * Objeto de termometro de água para inserir na tabela
	 * @param warm
	 * Boolean para verifica se o aquecedor está ligado ou não
	 */
	public void insert(WaterThermometer waterThermometer, boolean warm) {
		String sql = "INSERT INTO water_thermometer (temperature, warm, change_time) VALUES (?, ?, now())";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, waterThermometer.getTemperature());
			stmt.setBoolean(2, warm);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Insert error water termometer: " + e);
		}
	}

	/**
	 * Metodo que pega os registro na tabela
	 * @return Registros da tabela
	 */
	public List<WaterThermometerJb> select() {
		List<WaterThermometerJb> waterRegister = new ArrayList<WaterThermometerJb>();
		String sql = "SELECT * FROM water_thermometer";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				WaterThermometerJb waterThermometer = new WaterThermometerJb();
				waterThermometer.setId(rs.getLong("id"));
				waterThermometer.setTemperature(rs.getInt("temperature"));
				waterThermometer.setWarm(rs.getBoolean("warm"));

				Calendar data = Calendar.getInstance();
				
				data.setTime(rs.getTimestamp("change_time"));
				
				waterThermometer.setChange_time(data);

				waterRegister.add(waterThermometer);
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Select error water thermometer: " + e);
		}

		return waterRegister;
	}

}
