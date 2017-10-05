package br.fundatec.lpi.devices;

import java.util.Random;

import br.fundatec.lpi.interfaces.ThermometerFunctions;

/**
 * Clase que representa um Termometro de agua, possui a interface "ThermometerFunctions"
 * @author JP Martins, Ti11
 *
 */
public class WaterThermometer implements ThermometerFunctions {

	private int temperature;

	/**
	 * Construtor da classe, chama o metodo "takeTemperature" 
	 */
	public WaterThermometer() {
		takeTemperature();
	}
	
	@Override
	public int getTemperature() {
		return temperature;
	}
	
	@Override
	public void takeTemperature() {
		Random random = new Random();
		this.temperature = random.nextInt(13) + 17;//30
	}
}
