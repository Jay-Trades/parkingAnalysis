package parking.data;

public class PropertyData {
	private final String zipCode;
	private final Integer marketValue;
	private final Integer area;

	public PropertyData(String zipCode, Integer marketValue, Integer area) {
		this.zipCode = zipCode;
		this.marketValue = marketValue;
		this.area = area;
	}
	public String getZipCode() {
		return zipCode;
	}
	public Integer getMarketValue() {
		return marketValue;
	}
	
	public Integer getArea() {
		return area;
	}
}
