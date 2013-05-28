package br.com.jpa.table.strategy;

import java.lang.reflect.Method;

import br.com.jpa.table.column.ArrayColumn;
import br.com.jpa.table.column.Column;
import br.com.jpa.table.column.MethodColumn;

public interface MappingStrategy {

	/**
	 * used to create a column in general
	 * @return
	 */
	Column createColumn(Class<?> type);
	
	/**
	 * used to create a column when the object is an Array
	 * @param index
	 * @return
	 */
	ArrayColumn createArrayColumn(Class<?> type, int index);
	
	/**
	 * used to create a column when the object is returned by a method
	 * @param method
	 * @return
	 */
	MethodColumn createMethodColumn(Method method);
	
}
