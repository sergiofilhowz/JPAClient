package br.com.jpa.table;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import br.com.jpa.table.column.ArrayColumn;
import br.com.jpa.table.column.Column;
import br.com.jpa.table.strategy.MappingStrategy;

public class ColumnBuilderImpl implements ColumnBuilder {

	private ObjectMapping mapper;
	
	@Override
	public List<Column> buildColumns(List<Object> list) {
		List<Column> columns = new ArrayList<Column>();
		
		if(list.size() > 0) {
			
			Object object = list.get(0);
			Class<?> returnType = JpaClientUtils.fixReturnType(object.getClass());

			if(JpaClientUtils.isArray(object)) {
				createArrayColumns(list, columns);
			} else if(JpaClientUtils.isPrimitive(returnType)) {
				MappingStrategy strategy = mapper.getStrategy(returnType);
				columns.add(strategy.createColumn(returnType));
			} else {
				createMethodColumns(object, columns);
			}
			
		}
		
		return columns;
	}
	
	private void createArrayColumns(List<Object> list, List<Column> columns) {
		Object [] objectList = (Object []) list.get(0);
		
		for(int index = 0; index < objectList.length; index++) {
			Object obj = objectList[index];
			
			for (int i = 1; obj == null && i < list.size(); i++) {
				obj = ((Object [])list.get(i))[index];
			}
			
			Class<?> type = Object.class;
			ArrayColumn column = null;
			
			if(obj != null) {
				type = JpaClientUtils.fixReturnType(obj.getClass());
				
				MappingStrategy strategy = mapper.getStrategy(type);
				
				if(strategy != null) {
					column = strategy.createArrayColumn(type, index);
				}
				
			} else {
				column = new ArrayColumn("Object", Object.class, index);
			}
			
			columns.add(column);
			
		}
	}

	private void createMethodColumns(Object object, List<Column> columns) {
		
		Class<?> clazz = object.getClass();
		Method[] methods = clazz.getMethods();
		
		for (Method method : methods) {
			
			if (JpaClientUtils.isGetter(method)) {
				Column column = null;
				
				Class<?> returnType = method.getReturnType();
				returnType = JpaClientUtils.fixReturnType(returnType);
				
				MappingStrategy strategy = mapper.getStrategy(returnType);
				
				if(strategy != null) {
					column = strategy.createMethodColumn(method);
				}
			
				columns.add(column);
			}

		}
	}

	public ObjectMapping getMapper() {
		return mapper;
	}

	public void setMapper(ObjectMapping mapper) {
		this.mapper = mapper;
	}
	
}
