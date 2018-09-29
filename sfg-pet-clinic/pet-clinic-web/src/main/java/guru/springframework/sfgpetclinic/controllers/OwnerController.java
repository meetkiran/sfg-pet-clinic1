package guru.springframework.sfgpetclinic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import guru.springframework.sfgpetclinic.services.OwnerService;

@Controller
@RequestMapping("/owners") // any request goes to owner folder will be handled by this class
public class OwnerController {

	private final OwnerService ownerService;

	public OwnerController(OwnerService ownerService) {
		super();
		this.ownerService = ownerService;
	}

	@RequestMapping({ "/index", "/ownerList", "index.html" })
	public String listOwners(Model model) {
		model.addAttribute("ownerList", ownerService.findAll());
		return "owners/index";
	}
}
