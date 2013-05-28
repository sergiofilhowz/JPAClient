package br.com.jpa.table.column;

public class PrimitiveColumn extends Column {

	public PrimitiveColumn(String name, Class<?> type) {
		super(name, type);
	}

	@Override
	public Object getValue(Object object) {
		return object;
	}

}
