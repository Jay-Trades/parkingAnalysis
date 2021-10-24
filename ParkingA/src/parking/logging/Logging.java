
	package parking.logging;
	import java.io.File;
	import java.io.PrintWriter;

	public class Logging{
		private PrintWriter output;
		protected static String cacheFile = "cache.txt";
		
	//singleton
		private Logging(String cacheFile) {
		
			try {
				File log = new File(cacheFile);
						
				output = new PrintWriter(log);
			}
			catch(Exception e) {
		}
	}
		//instance of singleton
		private static Logging instance = null;
		
		public static void setName(String fileName) {
			cacheFile = fileName;
		}
		
		//getInstance method
		public static Logging getInstance() {
			if(instance == null) {
				instance = new Logging(cacheFile);
			}
			return instance;
		}
		/*
		 *When the program starts, write the current time followed by each of the runtime arguments
		 */
		public void writeCurrentTime(String[] args) {
			output.print(System.currentTimeMillis());
			for(String runTimeArgs: args) {
				output.print(" " + runTimeArgs);
		}
			output.println();
			output.flush();
		}
		/*Whenever an input file is opened for reading, the program 
		*write the current time and the name of the file to the log file.
		 */
		public void writeNameOfFile(String fileName) {
			output.println(System.currentTimeMillis() + fileName);
			output.flush();
		}
		/*
		 * When the user makes a choice write the current time and the user’s selection to the log file.
		 */
		public void writeCurrentTime_UserChoice(String choice) {
			output.println(System.currentTimeMillis() + choice);
			output.flush();
		}
		/*
		 * If the user enters a ZIP Code the program should write the current time and the specified ZIP Code to the log file.
		 */
		public void writeCurrentTime_Zipcode(String zip) {
			output.println(System.currentTimeMillis() + zip);
			output.flush();
		}
	}