package parking.processor;

import java.util.ArrayList;
import java.util.HashMap;

import parking.data.PropertyData;

public class GetTotalLivingData implements AverageValue{
		ArrayList<Integer> areaData = new ArrayList<Integer>();
		ArrayList<PropertyData> data;
		String zipCode;
		HashMap<String, ArrayList<PropertyData>> propertyData;
		
		GetTotalLivingData(HashMap<String, ArrayList<PropertyData>> propertyData, String zipCode) {
			this.propertyData = propertyData;
			this.zipCode = zipCode;
		}
		
		public ArrayList<Integer> getData() {
			data = propertyData.get(zipCode);
			if (data != null) {
				for (PropertyData d: data) {
					areaData.add(d.getArea());					
				}
			}
			return areaData;

		}

	}
