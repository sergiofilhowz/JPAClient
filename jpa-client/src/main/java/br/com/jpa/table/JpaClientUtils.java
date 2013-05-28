package br.com.jpa.table;

import java.lang.reflect.Method;
import java.sql.Date;

public class JpaClientUtils {

	private JpaClientUtils() {
		// Only static
	}
	
	/**
	 * FIXME skip @Transient annotated getters
	 * @param method
	 * @return true if method is a getter
	 */
	public static boolean isGetter(Method method) {
		String methodName = method.getName().toUpperCase();
		
		if(methodName.startsWith("IS") && method.getReturnType().isPrimitive()) {
			return true;
		}
		if(methodName.startsWith("GET")){
			return !methodName.equalsIgnoreCase("getClass");
		}
		
		return false;
	}

	/**
	 * Returns the method's name
	 * @param method
	 * @return the method's name
	 */
	public static String getName(Method method) {
		String methodName = method.getName().toUpperCase();
		String retorno = "";
		
		if (!isGetter(method)) {
			retorno = methodName;
		} else if (methodName.startsWith("IS")) {
			retorno = method.getName().substring(2);
		} else {
			retorno = method.getName().substring(3);
		}

		return retorno;
	}

	public static boolean isArray(Object object) {
		return Object[].class.isAssignableFrom(object.getClass());
	}
	
	public static boolean isPrimitive(Class<?> clazz) {
		return Boolean.class.isAssignableFrom(clazz)
				|| Long.class.isAssignableFrom(clazz)
				|| Integer.class.isAssignableFrom(clazz)
				|| Character.class.isAssignableFrom(clazz)
				|| Short.class.isAssignableFrom(clazz)
				|| Float.class.isAssignableFrom(clazz)
				|| Double.class.isAssignableFrom(clazz)
				|| Byte.class.isAssignableFrom(clazz)
				|| Date.class.isAssignableFrom(clazz)
				|| String.class.isAssignableFrom(clazz);
	}
	
	/**
	 * This method is necessary to convert from int to Integer and the other
	 * primitive types
	 * 
	 * @param returnType primitive
	 * @return wrapper Class
	 */
	public static Class<?> fixReturnType(Class<?> returnType) {
		if (returnType.isPrimitive()) {
			if(returnType.equals(Boolean.TYPE)){
				returnType = Boolean.class;
			} else if(returnType.equals(Long.TYPE)){
				returnType = Long.class;
			} else if(returnType.equals(Integer.TYPE)){
				returnType = Integer.class;
			} else if(returnType.equals(Character.TYPE)){
				returnType = Character.class;
			} else if(returnType.equals(Short.TYPE)){
				returnType = Short.class;
			} else if(returnType.equals(Float.TYPE)){
				returnType = Float.class;
			} else if(returnType.equals(Double.TYPE)){
				returnType = Double.class;
			} else if(returnType.equals(Byte.TYPE)){
				returnType = Byte.class;
			}
		}
		
		return returnType;
	}
	
}
