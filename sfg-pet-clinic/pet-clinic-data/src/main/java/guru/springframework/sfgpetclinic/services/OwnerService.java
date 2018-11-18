package guru.springframework.sfgpetclinic.services;

import java.util.List;
import java.util.Set;

import guru.springframework.sfgpetclinic.model.Owner;


/**
 * Created by jt on 7/18/18.
 */
public interface OwnerService extends CrudService<Owner, Long> {

    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);

	Set<Owner> findAll();
 }
