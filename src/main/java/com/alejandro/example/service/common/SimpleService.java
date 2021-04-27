package com.alejandro.example.service.common;

import java.io.Serializable;
import java.util.Set;

public interface SimpleService<E extends Serializable, ID> {
	
	public E findById(ID id);
	public Set<E> getAll();
	public E save(E entity);
	public void delete(ID id);
	public E update(E entity, ID id);

}
