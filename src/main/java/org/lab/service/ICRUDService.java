package org.lab.service;

import java.io.Serializable;
import java.util.List;

public interface ICRUDService<T> {  // interface methods are by default public

	T saveOrUpdate(T entity) throws Exception;

	T getById(Serializable id) throws Exception;

	List<T> getAll() throws Exception;
	
	void deleteById(Serializable id) throws Exception;;
	
	void delete(T entity) throws Exception;

}
/*
Java Generic Type

Java Generic Type Naming convention helps us understanding code easily and having a naming convention is one of the best practices of java programming language. So generics also comes with it’s own naming conventions. Usually type parameter names are single, uppercase letters to make it easily distinguishable from java variables. The most commonly used type parameter names are:

E – Element (used extensively by the Java Collections Framework, for example ArrayList, Set etc.)
K – Key (Used in Map)
N – Number
T – Type
V – Value (Used in Map)
S,U,V etc. – 2nd, 3rd, 4th types
*/