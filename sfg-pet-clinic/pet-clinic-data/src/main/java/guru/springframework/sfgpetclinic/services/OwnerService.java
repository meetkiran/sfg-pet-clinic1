package guru.springframework.sfgpetclinic.services;

import java.util.Set;

import guru.springframework.sfgpetclinic.model.Owner;

public interface OwnerService {
	
	Owner findByLastname(String string);
	Owner findById();
	Owner saveOwner();
	Set<Owner>findWAll();
}
