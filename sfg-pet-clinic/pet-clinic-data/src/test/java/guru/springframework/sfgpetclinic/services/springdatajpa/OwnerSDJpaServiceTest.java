package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

	@Mock
	private OwnerRepository ownerRepository;
	@Mock
	private PetRepository petRepository;
	@Mock
	private PetTypeRepository petTypeRepository;

	@InjectMocks
	OwnerSDJpaService service;
	Owner returnOwner;

	@BeforeEach
	void setUp() throws Exception {
		returnOwner = Owner.builder().id(1L).lastName("Smith").build();
	}

	/*
	 * @Test void testOwnerSDJpaService() { fail("Not yet implemented"); }
	 */

	@Test
	void testFindByLastName() {

		when(ownerRepository.findByLastName(any())).thenReturn(returnOwner);
		Owner owner = service.findByLastName("Smith");
		assertEquals("Smith", owner.getLastName());
	}

	/*
	 * @Test void testFindAllByLastNameLike() { fail("Not yet implemented"); }
	 * 
	 * @Test void testFindAll() { fail("Not yet implemented"); }
	 */

	@Test
	void testFindById() {
		when(ownerRepository.findById(1L)).thenReturn(Optional.of(returnOwner));
		Owner owner = service.findById(1L);
		assertNotNull(owner);
	}

	@Test
	void testFindByIdNotFound() {
		when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());
		Owner owner = service.findById(1L);
		assertNull(owner);
	}

	@Test
	void testSave() {
		Owner owner = service.save(returnOwner);
		verify(ownerRepository).save(any());
	}

	@Test
	void testDelete() {
		service.delete(returnOwner);
		verify(ownerRepository, times(1)).delete(any());
	}
	/*
	 * @Test void testDeleteById() { fail("Not yet implemented"); }
	 */
}
