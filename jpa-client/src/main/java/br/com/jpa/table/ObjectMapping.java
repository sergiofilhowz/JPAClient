package br.com.jpa.table;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import br.com.jpa.table.strategy.CollectionStrategy;
import br.com.jpa.table.strategy.MappingStrategy;
import br.com.jpa.table.strategy.ObjectStrategy;
import br.com.jpa.table.strategy.PrimitiveStrategy;

public class ObjectMapping {

	private Map<Class<?>, MappingStrategy> mapping;
	
	private PrimitiveStrategy primitiveStrategy;
	
	private ObjectStrategy objectStrategy;
	
	private CollectionStrategy collectionStrategy;

	/**
	 * Returns the strategy in the closest hierarchy the object class
	 * @param clazz The type of the object
	 * @return null if no class is found
	 */
	public MappingStrategy getStrategy(Class<?> clazz) {
		clazz = JpaClientUtils.fixReturnType(clazz);
		
		MappingStrategy mappingStrategy = getMapping().get(clazz);
		
		if(mappingStrategy == null) {
			List<Class<?>> list = new ArrayList<Class<?>>();
			Iterator<Class<?>> iterator = getMapping().keySet().iterator();
			while(iterator.hasNext()) {
				Class<?> index = iterator.next();
				if(index.isAssignableFrom(clazz)) {
					list.add(index);
				}
			}
			
			if(list.size() >= 1) {
				
				Class<?> child = list.get(0);
				for (Class<?> c : list) {
					if(child.isAssignableFrom(c)) {
						child = c;
					}
				}
				mappingStrategy = getMapping().get(child);
			}
		}
		
		if(mappingStrategy == null) {
			if(JpaClientUtils.isPrimitive(clazz)) {
				mappingStrategy = getPrimitiveStrategy();
			} else if(Collection.class.isAssignableFrom(clazz)) {
				mappingStrategy = getCollectionStrategy();
			} else {
				mappingStrategy = getObjectStrategy();
			}
		}
		
		return mappingStrategy;
	}

	public PrimitiveStrategy getPrimitiveStrategy() {
		return primitiveStrategy;
	}

	public void setPrimitiveStrategy(PrimitiveStrategy primitiveStrategy) {
		this.primitiveStrategy = primitiveStrategy;
	}

	public ObjectStrategy getObjectStrategy() {
		return objectStrategy;
	}

	public void setObjectStrategy(ObjectStrategy objectStrategy) {
		this.objectStrategy = objectStrategy;
	}

	public CollectionStrategy getCollectionStrategy() {
		return collectionStrategy;
	}

	public void setCollectionStrategy(CollectionStrategy collectionStrategy) {
		this.collectionStrategy = collectionStrategy;
	}

	public Map<Class<?>, MappingStrategy> getMapping() {
		return mapping;
	}

	public void setMapping(Map<Class<?>, MappingStrategy> mapping) {
		this.mapping = mapping;
	}
	
}
