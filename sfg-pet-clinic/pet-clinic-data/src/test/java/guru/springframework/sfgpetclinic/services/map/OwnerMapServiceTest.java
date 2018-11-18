package guru.springframework.sfgpetclinic.services.map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import guru.springframework.sfgpetclinic.model.Owner;

class OwnerMapServiceTest {

	OwnerMapService ownerMapService;
	final Long ownerId = 1L;
	final String lname = "Smith";

	@BeforeEach
	void setUp() throws Exception {
		this.ownerMapService = new OwnerMapService(new PetTypeMapService(), new PetMapService());
		this.ownerMapService.save(Owner.builder().id(ownerId).lastName(lname).build());
	}

	@Test
	void testFindAll() {
		Set<Owner> owners = this.ownerMapService.findAll();
		assertEquals(1, owners.size());
	}

	@Test
	void testFindByIdLong() {
		Owner owner = ownerMapService.findById(ownerId);
		assertEquals(ownerId, owner.getId());
	}

	@Test
	void testSave() {
		Long id = 2L;
		Owner owner2 = this.ownerMapService.save(Owner.builder().build());
		assertNotNull(owner2);
	}

	@Test
	void testDelete() {
		this.ownerMapService.delete(this.ownerMapService.findById(ownerId));
		assertEquals(0, this.ownerMapService.findAll().size());
	}

	@Test
	void testDeleteByIdLong() {
		this.ownerMapService.deleteById(ownerId);
		assertEquals(0, this.ownerMapService.findAll().size());
	}

}
