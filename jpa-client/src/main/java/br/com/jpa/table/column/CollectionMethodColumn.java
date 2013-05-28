package br.com.jpa.table.column;

import java.lang.reflect.Method;
import java.util.Collection;

public class CollectionMethodColumn extends MethodColumn {

	public CollectionMethodColumn(String name, Method method) {
		super(name, Integer.class, method);
	}
	
	@Override
	public Object getValue(Object object) {
		Collection<?> collection = (Collection<?>) super.getValue(object);
		return collection.size();
	}
	
}
