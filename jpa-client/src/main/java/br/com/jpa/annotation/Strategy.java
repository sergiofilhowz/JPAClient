package br.com.jpa.annotation;

/**
 * This annotation indicates that the class is a strategy for some class
 * 
 * @author sergiocastro
 *
 */
public @interface Strategy {

	/**
	 * @return class the strategy works with
	 */
	Class<?> forClass();
	
}
