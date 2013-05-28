package br.com.jpa.table;

import java.util.List;

import br.com.jpa.table.column.Column;

public interface ColumnBuilder {

	/**
	 * @param list list of objects returned by jpa query
	 * @return the list with all columns
	 */
	List<Column> buildColumns(List<Object> list);
	
}
