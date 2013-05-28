package br.com.jpa.loader;

import java.util.Iterator;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import br.com.jpa.annotation.Strategy;
import br.com.jpa.table.ObjectMapping;
import br.com.jpa.table.strategy.MappingStrategy;
import br.com.jpa.window.JpaClientWindow;

public class JpaClientLoader extends ClassPathXmlApplicationContext {

	public JpaClientLoader(String configLocation) throws BeansException {
		super(configLocation);
	}

	public static void main(String[] args) {
		ApplicationContext applicationContext = new JpaClientLoader(
				"/application-context.xml");
		
		ObjectMapping mapping = applicationContext.getBean(ObjectMapping.class);
		Map<String, MappingStrategy> strategies = applicationContext
				.getBeansOfType(MappingStrategy.class);
		Iterator<String> iterator = strategies.keySet().iterator();
		
		// FIXME ainda corrigir
		while(iterator.hasNext()) {
			String key = iterator.next();
			MappingStrategy strategy = strategies.get(key);
			if(strategy.getClass().isAnnotationPresent(Strategy.class)) {
				Strategy annotation = strategy.getClass().getAnnotation(
						Strategy.class);
				mapping.getMapping().put(annotation.forClass(), strategy);
			} else {
				// TODO throw an exception warning the strategy doesn't have 
				// the required annotation
			}
		}
		
		JpaClientWindow window = new JpaClientWindow(applicationContext);
		window.setVisible(true);
	}

}
