package br.com.jpa.table.strategy;

import java.lang.reflect.Method;

import br.com.jpa.table.JpaClientUtils;
import br.com.jpa.table.column.ArrayColumn;
import br.com.jpa.table.column.Column;
import br.com.jpa.table.column.MethodColumn;
import br.com.jpa.table.column.ObjectArrayColumn;
import br.com.jpa.table.column.ObjectMethodColumn;
import br.com.jpa.table.column.PrimitiveColumn;

public class ObjectStrategy implements MappingStrategy {

	@Override
	public Column createColumn(Class<?> type) {
		return new PrimitiveColumn(type.getSimpleName(), String.class);
	}

	@Override
	public ArrayColumn createArrayColumn(Class<?> type, int index) {
		return new ObjectArrayColumn(type.getSimpleName(), index);
	}

	@Override
	public MethodColumn createMethodColumn(Method method) {
		return new ObjectMethodColumn(JpaClientUtils.getName(method), method);
	}

}
