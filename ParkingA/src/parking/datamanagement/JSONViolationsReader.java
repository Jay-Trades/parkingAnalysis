package parking.datamanagement;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import parking.data.PropertyData;
import parking.data.ViolationsData;

public class JSONViolationsReader implements Reader {
	protected String filename;

	public JSONViolationsReader(String filename) {
		this.filename = filename;
	}

	public HashMap<String, ArrayList<ViolationsData>> getAllData() {
		HashMap<String, ArrayList<ViolationsData>> violationsData = new HashMap<String, ArrayList<ViolationsData>>();
		String zipCode;
		Integer fine;
		String state;
		String reason;
		JSONParser parser = new JSONParser();
		// open the file and get the array of JSON objects
		JSONArray dataArray = null;

		try {
			dataArray = (JSONArray)parser.parse(new FileReader(filename));
			// use an iterator to iterate over each element of the array
			Iterator<JSONObject> iter = dataArray.iterator();
			// iterate while there are more objects in array
			while (iter.hasNext()) {
				// get the next JSON object
				JSONObject data = iter.next();
				zipCode = (String) data.get("zip_code");
				state = (String) data.get("state");
				reason = (String) data.get("violation");

				try {
					long tempFine = (long) data.get("fine");
					fine = (int) tempFine; //try casting to int 
				}catch (NumberFormatException e){
					e.printStackTrace();
					fine = null;
				}catch (ClassCastException e){
					e.printStackTrace();
					fine = null;
				}

				
				if(zipCode == null ||zipCode.length() < 5) {
					continue;
				} else {
					zipCode = zipCode.substring(0,5);
				}
				
				try {
					Integer.parseInt(zipCode);
				}catch (NumberFormatException e){
					continue;
				}
								
				ViolationsData dataObj = new ViolationsData(zipCode, fine, state, reason);
				if (violationsData.get(zipCode) == null) {
					ArrayList<ViolationsData> violationsList = new ArrayList<ViolationsData>();
					violationsList.add(dataObj);
					violationsData.put(zipCode, violationsList);
				} else {
					violationsData.get(zipCode).add(dataObj);
				}
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return violationsData;
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return violationsData;
		}
		return violationsData;
	}
}
