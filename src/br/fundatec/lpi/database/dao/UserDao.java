package br.fundatec.lpi.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.fundatec.lpi.database.javabean.UserJb;
import br.fundatec.lpi.devices.User;
import br.fundatec.lpi.interfaces.DataBaseComandsDao;

/**
 * Classe que realiza as interações com a tabela "user_prioprity" no banco de
 * dados
 * 
 * @author JP Martins, Ti11
 *
 */
public class UserDao implements DataBaseComandsDao<User, UserJb> {

	private Connection con;

	/**
	 * Construtor da classe, estabelece uma conecao
	 * 
	 * @param con
	 */
	public UserDao(Connection con) {
		this.con = con;
	}

	/**
	 * Metodo usado para criar a tebela
	 */
	public void create() {
		String sql = "create table user_priority( id serial primary key, max_weight int, ideal_temperature int);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void insert(User user) {
		String sql = "INSERT INTO user_priority (max_weight, ideal_temperature) VALUES (?,?)";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, user.getMaxWeight());
			stmt.setInt(2, user.getIdealTemperature());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Insert error user: " + e);
		}
	}

	@Override
	public UserJb select() {
		UserJb userRegister = new UserJb();
		String sql = "SELECT * FROM user_priority ORDER BY id DESC LIMIT 1";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				userRegister.setId(rs.getLong("id"));
				userRegister.setIdeal_temperature(rs.getInt("ideal_temperature"));
				userRegister.setMax_wheight(rs.getInt("max_weight"));
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Select error user: " + e);
		}
		return userRegister;
	}

}
