package br.fundatec.lpi.devices;

import br.fundatec.lpi.interfaces.WarmWaterFunctions;

/**
 * Interface que representa um aquecedor de agua, possui a interface
 * "WarmWaterFunctions"
 * 
 * @author JP Martins, Ti11
 *
 */
public class WaterWarmer implements WarmWaterFunctions {

	private boolean status;
	
	
	public WaterWarmer() {
		this.status = false;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public boolean getStatus() {
		return status;
	}

	@Override
	public String warmWater() {
		return "Warming Water to ideal temperature";
	}

}
