package br.com.jpa.table;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import br.com.jpa.table.column.Column;

public class ReflectionTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 6216994348692761006L;

	private List<Column> columns;
	private List<Object> rows;
	
	public ReflectionTableModel(List<Object> rows, List<Column> columns) {
		this.columns = columns;
		this.rows = rows;
	}
	
	@Override
	public int getColumnCount() {
		return columns.size();
	}

	@Override
	public int getRowCount() {
		return rows.size();
	}

	@Override
	public String getColumnName(int columnIndex) {
		return columns.get(columnIndex).getName();
	}
	
	@Override
	public Class<?> getColumnClass(int columnIndex) {
		Column column = columns.get(columnIndex);
		return column.getType();
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object row = rows.get(rowIndex);
		Column column = columns.get(columnIndex);
		return column.getRawValue(row);
	}

}
