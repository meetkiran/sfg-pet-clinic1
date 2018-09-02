package guru.springframework.sfgpetclinic;

public class SomeController {

	private final MyService myService;

	public SomeController(MyService myService) {
		super();
		this.myService = myService;
	}
	
}
