package br.fundatec.lpi.database.conectionfactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe que gera uma conecao com o banco
 * @author JP Martins, Ti11
 *
 */
public class ConnectionFactory {
	
	/**
	 * Metodo que gera a conecao
	 * @return Conecao
	 */
	public Connection fabricate() {
		try {
			return DriverManager.getConnection("jdbc:postgresql://localhost:5432/IOT","postgres","postgres");
		} catch (SQLException e) {
			throw new RuntimeException("Connection error");
		}
	}
}
