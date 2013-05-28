package br.com.jpa.table.column;

import java.lang.reflect.Method;

public class ObjectMethodColumn extends MethodColumn {

	public ObjectMethodColumn(String name, Method method) {
		super(name, String.class, method);
	}
	
	@Override
	public Object getValue(Object object) {
		Object o  = super.getValue(object); 
		return o != null ? o.toString() : "";
	}

}
