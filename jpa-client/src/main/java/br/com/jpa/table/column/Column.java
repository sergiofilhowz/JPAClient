package br.com.jpa.table.column;

import java.util.Date;

import br.com.jpa.table.DateFormatter;

public abstract class Column {

	private String name;
	private Class<?> type;
	
	public Column(String name, Class<?> type) {
		super();
		this.name = name;
		this.type = type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getType() {
		return Date.class.isAssignableFrom(type) ? String.class : type;
	}
	public void setType(Class<?> type) {
		this.type = type;
	}

	public Object getRawValue(Object object) {
		Object returnedValue = getValue(object);
		
		if(Date.class.isAssignableFrom(type)) {
			Date date = (Date) returnedValue;
			returnedValue = DateFormatter.format(date);
		}
		
		return returnedValue;
	}
	
	protected abstract Object getValue(Object object);
	
}
