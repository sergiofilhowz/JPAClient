package br.com.jpa.table.column;

public class ObjectArrayColumn extends ArrayColumn {

	public ObjectArrayColumn(String name, int index) {
		super(name, String.class, index);
	}

	@Override
	public Object getValue(Object object) {
		Object o  = super.getValue(object); 
		return o != null ? o.toString() : "";
	}
}
