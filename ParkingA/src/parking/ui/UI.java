package parking.ui;


import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import parking.data.ViolationsData;
import parking.logging.Logging;
import parking.processor.FinesProcessor;
import parking.processor.FrequentFineProcessor;
import parking.processor.MarketValueProcessor;
import parking.processor.PopulationProcessor;
import parking.processor.PropertyProcessor;

public class UI {
	protected PopulationProcessor populationProcessor;
	protected FinesProcessor fines;
	protected PropertyProcessor property;
	protected MarketValueProcessor market;
	protected FrequentFineProcessor frequent;

	protected Scanner in;
	public UI(PopulationProcessor population, FinesProcessor fines, PropertyProcessor property, MarketValueProcessor market, FrequentFineProcessor frequent) {
		this.populationProcessor = population;
		this.fines = fines;
		this.property = property;
		this.market = market;
		this.frequent = frequent;
	}

	public void start() {
		System.out.println("b4 calling getmarketvalue");

		System.out.println("population total");
		System.out.println(populationProcessor.getTotalPopulation());
		System.out.println("fines per capita");
		fines.getFinePerCapita();
		property.getAvgMarketValue("99999");
		System.out.println("calling get avg area");
		property.getAvgAreaValue("99999");
		market.getMarketPerCap("19102");
		frequent.getFrequentFine();

	}
}
