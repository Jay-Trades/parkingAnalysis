package parking.datamanagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parking.data.PropertyData;
import parking.data.ViolationsData;

public interface Reader {
		public HashMap<String, ArrayList<ViolationsData>> getAllData();
}

