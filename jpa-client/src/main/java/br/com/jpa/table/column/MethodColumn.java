package br.com.jpa.table.column;

import java.lang.reflect.Method;

import br.com.jpa.table.TableModelException;

public class MethodColumn extends Column {

	private Method method;
	
	public MethodColumn(String name, Class<?> type, Method method) {
		super(name, type);
		this.method = method;
	}

	@Override
	public Object getValue(Object object) {
		try {
			return method.invoke(object);
		} catch (Exception error) {
			throw new TableModelException("An error ocurred while recovering a value", error);
		}
	}
	
}
