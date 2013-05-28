package br.com.jpa.table.strategy;

import java.lang.reflect.Method;

import br.com.jpa.table.column.ArrayColumn;
import br.com.jpa.table.column.Column;
import br.com.jpa.table.column.MethodColumn;
import br.com.jpa.table.column.PrimitiveColumn;

public class PrimitiveStrategy implements MappingStrategy {

	@Override
	public MethodColumn createMethodColumn(Method method) {
		return new MethodColumn(method.getName(), method.getReturnType(), method);
	}

	@Override
	public Column createColumn(Class<?> type) {
		return new PrimitiveColumn(type.getSimpleName(), type);
	}

	@Override
	public ArrayColumn createArrayColumn(Class<?> type, int index) {
		return new ArrayColumn(type.getSimpleName(), type, index);
	}


}
