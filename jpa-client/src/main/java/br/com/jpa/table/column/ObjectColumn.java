package br.com.jpa.table.column;

public class ObjectColumn extends Column {

	public ObjectColumn(String name) {
		super(name, String.class);
	}

	@Override
	protected Object getValue(Object object) {
		return object.toString();
	}

}
