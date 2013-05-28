package br.com.jpa.table;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	private SimpleDateFormat dateFormat;
	
	private static DateFormatter instance;
	
	private DateFormatter() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	private String privateFormat(Date date) {
		return dateFormat.format(date);
	}

	public static String format(Date date) {
		return getInstance().privateFormat(date);
	}
	
	private static DateFormatter getInstance() {
		if(instance == null) {
			instance = new DateFormatter();
		}
		
		return instance;
	}
	
}
