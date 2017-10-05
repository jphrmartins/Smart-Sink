package br.fundatec.lpi.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.fundatec.lpi.database.javabean.SinkJb;
import br.fundatec.lpi.devices.Sink;
import br.fundatec.lpi.interfaces.DataBaseComandsDao;

/**
 * Classe que realiza as interações com a tabela "sink" no banco de dados
 * 
 * @author JP Martins, Ti11
 *
 */
public class SinkDao implements DataBaseComandsDao<Sink, List<SinkJb>> {

	private Connection con;

	/**
	 * Construtor da classe, estabelece uma conecao
	 * 
	 * @param con
	 */
	public SinkDao(Connection con) {
		super();
		this.con = con;
	}

	/**
	 * Metodo usado para criar a tebela
	 */
	public void create() {
		String sql = "create table sink( id serial primary key, weight int, change_time timestamp);";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insert(Sink sink) {
		String sql = "INSERT INTO sink (weight, change_time) VALUES (?, now())";

		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, sink.getWeight());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Insert error sink: " + e);
		}
	}

	public List<SinkJb> select() {
		List<SinkJb> sinkChanges = new ArrayList<SinkJb>();
		String sql = "SELECT * FROM sink";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				SinkJb sink = new SinkJb();
				sink.setId(rs.getLong("id"));
				sink.setWheight(rs.getDouble("weight"));

				Calendar date = Calendar.getInstance();
				Calendar dateTime = Calendar.getInstance();
				
				date.setTime(rs.getDate("change_time"));
				dateTime.setTime(rs.getTime("change_time"));
				
				sink.setChangingTime(date, dateTime);
				sinkChanges.add(sink);
			}
			stmt.close();
		} catch (SQLException e) {
			System.out.println("Insert error sink: " + e);
		}

		return sinkChanges;
	}
}
