package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")//any request goes to owner folder will be handled by this class
public class OwnerController {
	
	@RequestMapping({"/index","/ownerList","index.html"})
	public String listOwners() {
		
		return "owners/index";
	}
}
