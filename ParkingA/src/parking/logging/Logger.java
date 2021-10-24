package parking.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//This class will log the time and data to a file.
public class Logger {
	private PrintWriter out;
	String filename;
	
	private Logger() {
	};
	
	public void setFile(String fileName) {
		this.filename = fileName; 
		
		try { 
			FileWriter fr = new FileWriter(filename, true);
			out = new PrintWriter(fr);
			
		} catch (IOException e) {
			System.out.println("An error occurred when trying to open a filewriter.");
		}
	}
	private static Logger instance = null;
	
	public static Logger getInstance() {
		if (instance == null) {
			Logger instance = new Logger();
			return instance;
		}else {
			return instance;
		}
	}
	
	public void log(String msg) {
		out.println(Long.toString(System.currentTimeMillis()) + " " + msg);
		out.flush();
	}
	
}

//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class Logger {
//	private PrintWriter out;
//	String filename;
//	
//	private Logger() {
//	};
//	
//	public void setFile(String fileName) {
//		this.filename = fileName; 
//		
//		try { 
//			FileWriter fr = new FileWriter(filename);
//			out = new PrintWriter(fr);
//			
//		} catch (IOException e) {
//			System.out.println("An error occurred when trying to open a filewriter.");
//		}
//	}
//	private static Logger instance = new Logger();
//	
//	public static Logger getInstance() {return instance;}
//	
//	public void log(String msg) {
//		out.println(msg);
//		out.flush();
//	}
//	
//	
//}
