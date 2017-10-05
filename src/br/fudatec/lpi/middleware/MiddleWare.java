package br.fudatec.lpi.middleware;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.fundatec.lpi.database.conectionfactory.ConnectionFactory;
import br.fundatec.lpi.database.dao.SinkDao;
import br.fundatec.lpi.database.dao.UserDao;
import br.fundatec.lpi.database.dao.WaterThermometerDao;
import br.fundatec.lpi.database.javabean.SinkJb;
import br.fundatec.lpi.database.javabean.UserJb;
import br.fundatec.lpi.database.javabean.WaterThermometerJb;
import br.fundatec.lpi.devices.Sink;
import br.fundatec.lpi.devices.User;
import br.fundatec.lpi.devices.WaterThermometer;
import br.fundatec.lpi.devices.WaterWarmer;

/**
 * Classe middleWare, é nela que sera feita todas as interações entre os
 * aparelhos e o app.
 * 
 * @author JP Martins, TI11
 *
 */
public class MiddleWare {

	/**
	 * Construtor da classe, chama o metodo "setPriority"
	 * 
	 * @param maxWeight
	 *            Peso maximo
	 * @param idealTemperature
	 *            Temperatura ideal.
	 * @throws SQLException
	 *             Tratamento de execao em caso de erro com o banco
	 */
	public MiddleWare(int maxWeight, int idealTemperature) throws SQLException {
		setPriority(maxWeight, idealTemperature);
	}

	/**
	 * Atribui as prioriedades do usuario, definindo peso maximo e temperatura
	 * ideal
	 * 
	 * @param maxWeight
	 *            Peso Maximo desejado pelo usuario
	 * @param idealTemperature
	 *            Temperatura ideal desejada pelo usuario
	 * @throws SQLException
	 *             Tratamento de execao em caso de erro com o banco
	 */
	public void setPriority(int maxWeight, int idealTemperature) throws SQLException {
		User user = new User(maxWeight, idealTemperature);
		Connection con = new ConnectionFactory().fabricate();
		UserDao uDao = new UserDao(con);
		uDao.insert(user);
		con.close();
		System.out.println("User's priorities set");
	}

	/**
	 * Metodo que verifica o peso da pia e compara com o peso maximo definido
	 * pelo usuario
	 * 
	 * @return Uma mensagem informando o estado atual da louca e seu peso
	 * @throws SQLException
	 *             Tratamento de execao em caso de erro com o banco
	 */
	public String verifySinkWeight() throws SQLException {
		Sink sink = new Sink();
		Connection con = new ConnectionFactory().fabricate();
		SinkDao sinkDao = new SinkDao(con);
		UserJb user = selectUserPriority(con);

		if (sinkWeightOneQuarterUserMaxWeight(sink, user)) {
			saveSinkDatabase(sink, con, sinkDao);
			return "Dishe Weight: " + sink.getWeight() + " grams";
		} else if (sinkWeightBetweenOneAndThreeQuarterUserMaxWeight(sink, user)) {
			saveSinkDatabase(sink, con, sinkDao);
			return "Sink getting full... Dish Weight: " + sink.getWeight() + " grams";
		}
		saveSinkDatabase(sink, con, sinkDao);
		return "Sink Full, Go Wash the dishes.... Dish Weight: " + sink.getWeight() + " grams";
	}

	/**
	 * Metodo para verificar as prioriedades do usario, tais como peso maximo e temperatura ideal
	 * @param con
	 * Coneccao para se establecer com o banco
	 * @return Prioreidades do usuario
	 */
	private UserJb selectUserPriority(Connection con) {
		UserDao uDao = new UserDao(con);
		UserJb user = uDao.select();
		return user;
	}

	/**
	 * Verifica se o peso da pia esta entre 1/4 e 3/4 do peso maixmo informado pelo usuario
	 * @param sink
	 * peso da pia
	 * @param user
	 * prioreidades do usuario
	 * @return se o peso da pia esta entre 1/4 e 3/4 do peso maixmo informado pelo usuario
	 */
	private boolean sinkWeightBetweenOneAndThreeQuarterUserMaxWeight(Sink sink, UserJb user) {
		return sink.getWeight() > (user.getMax_weight() / 4) && sink.getWeight() <= ((3 * user.getMax_weight()) / 4);
	}

	/**
	 * Verifica se o peso da pia esta até 1/4 do peso maximo informado pelo usuario
	 * @param sink
	 * peso da pia
	 * @param user
	 * prioreidades do usuario
	 * @return se o peso da pia esta até 1/4 do peso máximo informado pelo usuario
	 */
	private boolean sinkWeightOneQuarterUserMaxWeight(Sink sink, UserJb user) {
		return sink.getWeight() <= (user.getMax_weight() / 4);
	}

	private void saveSinkDatabase(Sink sink, Connection con, SinkDao sinkDao) throws SQLException {
		sinkDao.insert(sink);
		con.close();
	}

	public String waterTemperatureChange() throws SQLException {
		Connection con = new ConnectionFactory().fabricate();
		WaterThermometer waterTerm = new WaterThermometer();
		WaterWarmer warmer = new WaterWarmer();
		WaterThermometerDao wDao = new WaterThermometerDao(con);
		UserJb userPriority = selectUserPriority(con);

		if (waterTerm.getTemperature() < userPriority.getIdeal_temperature()) {
			warmer.setStatus(true);
			saveWaterThermometerDatabase(con, waterTerm, wDao, warmer.getStatus());
			return warmer.warmWater();
		}
		saveWaterThermometerDatabase(con, waterTerm, wDao, warmer.getStatus());
		return "Water already warm";
	}

	private void saveWaterThermometerDatabase(Connection con, WaterThermometer waterTerm, WaterThermometerDao wDao,
			boolean warm) throws SQLException {
		wDao.insert(waterTerm, warm);
		con.close();
	}

	public String takeRecords() throws SQLException {
		Connection con = new ConnectionFactory().fabricate();
		SinkDao sDao = new SinkDao(con);
		WaterThermometerDao wDao = new WaterThermometerDao(con);
		UserDao uDao = new UserDao(con);
		String records = "";
		records = userRecords(uDao, records);
		records = sinkRecords(sDao, records);
		records = waterThermometerRecords(wDao, records);
		con.close();
		return records;
	}

	private String userRecords(UserDao uDao, String records) {
		UserJb user = uDao.select();
		records = "User ID: " + user.getId() + "\n";
		records += "User IDEAL TEMPERATURE: " + user.getIdeal_temperature() + "\n";
		records += "User MAX WEIGHT: " + user.getMax_weight() + "\n";
		records += space(records);
		records += space(records);
		return records;
	}

	private String space(String space) {
		space = "--------------------------------------------\n";
		return space;
	}

	private String waterThermometerRecords(WaterThermometerDao wDao, String records) {
		List<WaterThermometerJb> waterThermometer = new ArrayList<WaterThermometerJb>();
		waterThermometer = wDao.select();
		for (WaterThermometerJb waterThermometerJB : waterThermometer) {
			records += "Water Thermometer ID: " + waterThermometerJB.getId() + "\n";
			records += "Water Thermometer TEMPERATURE: " + waterThermometerJB.getTemperature() + "\n";
			records += "Water Thermometer CHANGE TIME: " + waterThermometerJB.getChange_time().getTime() + "\n";
			if (waterThermometerJB.isWarm()) {
				records += "Water Thermometer WARM SYSTEM: ON " + "\n";
			} else {
				records += "Water Thermometer WARM SYSTEM: OFF " + "\n";
			}
			records += space(records);
		}
		records += space(records);
		return records;
	}

	private String sinkRecords(SinkDao sDao, String records) {
		List<SinkJb> sink = new ArrayList<SinkJb>();
		sink = sDao.select();
		for (SinkJb sinkJB : sink) {
			records += "Sink ID: " + sinkJB.getId() + "\n";
			records += "Sink WHEIGHT: " + sinkJB.getWheight() + "\n";
			records += "Sink CHANGETIME: " + sinkJB.getChangingTime().getTime() + "\n";
			records += space(records);
		}
		records += space(records);
		return records;
	}
}