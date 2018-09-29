package guru.springframework.sfgpetclinic.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;

@Component
public class DataLoader implements CommandLineRunner {// this interface is used to initialize data{

	private final OwnerService ownerService;
	private final VetService vetService;

	@Override
	public void run(String... args) throws Exception {
		Owner mOwner = new Owner();
		Owner mOwner1 = new Owner();
		Vet mVet = new Vet();
		Vet mVet1 = new Vet();
		
		mOwner.setId(1L);
		mOwner.setFirstName("f1");
		mOwner.setLastName("l1");
		mOwner1.setId(2L);
		mOwner1.setFirstName("f2");
		mOwner1.setLastName("l2");
		ownerService.save(mOwner);
		ownerService.save(mOwner1);
		System.out.println(">>>>>>owner saved");
		mVet.setId(3L);
		mVet.setLastName("l3");
		mVet.setFirstName("f3");
		mVet1.setId(4L);
		mVet1.setLastName("l4");
		mVet1.setFirstName("f4");
		vetService.save(mVet1);
		vetService.save(mVet);
		System.out.println(">>>>>>vet saved");
	}

	public DataLoader() {
		super();
		this.ownerService = new OwnerServiceMap();
		this.vetService = new VetServiceMap();
	}

}
