package parking.processor;

import java.util.ArrayList;
import java.util.HashMap;

import parking.data.PropertyData;

public class GetMarketValueData implements AverageValue{
	ArrayList<Integer> valueData = new ArrayList<Integer>();
	ArrayList<PropertyData> data;
	String zipCode;
	HashMap<String, ArrayList<PropertyData>> propertyData;
	
	GetMarketValueData(	HashMap<String, ArrayList<PropertyData>> propertyData, String zipCode) {
		this.propertyData = propertyData;
		this.zipCode = zipCode;

	}
	
	public ArrayList<Integer> getData() {
		data = propertyData.get(zipCode);
		if (data != null ) {
			for (PropertyData d: data) {
				valueData.add(d.getMarketValue());
			}
		}
		return valueData;

	}

}
