package br.com.jpa.table.strategy;

import java.lang.reflect.Method;

import br.com.jpa.table.JpaClientUtils;
import br.com.jpa.table.column.ArrayColumn;
import br.com.jpa.table.column.CollectionMethodColumn;
import br.com.jpa.table.column.Column;
import br.com.jpa.table.column.MethodColumn;

public class CollectionStrategy implements MappingStrategy {

	@Override
	public Column createColumn(Class<?> type) {
		// it's never used
		return null;
	}

	@Override
	public ArrayColumn createArrayColumn(Class<?> type, int index) {
		// it's never used
		return null;
	}

	@Override
	public MethodColumn createMethodColumn(Method method) {
		return new CollectionMethodColumn(JpaClientUtils.getName(method), method);
	}

}
