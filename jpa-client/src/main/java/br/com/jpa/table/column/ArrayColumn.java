package br.com.jpa.table.column;

public class ArrayColumn extends Column {

	private int index;
	
	public ArrayColumn(String name, Class<?> type, int index) {
		super(name, type);
		this.index = index;
	}

	@Override
	public Object getValue(Object object) {
		return ((Object[])object)[index];
	}
	
}
