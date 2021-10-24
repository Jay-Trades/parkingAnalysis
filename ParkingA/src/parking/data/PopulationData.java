package parking.data;

public class PopulationData {
	private final String zipCode;
	private final Integer population;
	public PopulationData(String zipCode, Integer population) {
		this.zipCode = zipCode;
		this.population = population;
	}
	public String getZipCode() {
		return zipCode;
	}
	public Integer getPopulation() {
		return population;
	}
}

