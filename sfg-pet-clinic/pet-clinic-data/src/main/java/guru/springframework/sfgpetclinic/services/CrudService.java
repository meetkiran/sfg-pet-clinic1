/**
 * 
 */
package guru.springframework.sfgpetclinic.services;

import java.util.Set;

/**
 * @author MORAYA
 *
 */
public interface CrudService<T, ID> {
	Set<T> findAll();

	T findById(Long id);

	T save(T object);

	void delete(T object);
	
	void deleteById(ID id);
}
