package br.fundatec.lpi.app;

import java.sql.Connection;
import java.sql.SQLException;

import br.fudatec.lpi.middleware.MiddleWare;
import br.fundatec.lpi.database.conectionfactory.ConnectionFactory;
import br.fundatec.lpi.database.dao.SinkDao;
import br.fundatec.lpi.database.dao.UserDao;
import br.fundatec.lpi.database.dao.WaterThermometerDao;

/**
 * Classe de app, usada para testar o middleware
 * 
 * @author JP Martins, TI11
 *
 */
public class App {

	public static void main(String[] args) throws SQLException {

		// criando as tabelas, pra facilitar o trabalho sor. :)
		Connection con = new ConnectionFactory().fabricate();
		SinkDao sDao = new SinkDao(con);
		UserDao uDao = new UserDao(con);
		WaterThermometerDao wDao = new WaterThermometerDao(con);
		sDao.create();
		uDao.create();
		wDao.create();
		con.close();
		// fim da criacao, nao se esquece do cometar esse bloco de comandos
		// depois de
		// criar as tabelas.

		MiddleWare middleWare = new MiddleWare(2000, 25);
		System.out.println(middleWare.verifySinkWeight());
		System.out.println(middleWare.waterTemperatureChange());
		System.out.println(middleWare.takeRecords());
	}
}
