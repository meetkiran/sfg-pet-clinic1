package guru.springframework.sfgpetclinic.controllers;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

	@Mock
	OwnerService ownerService;

	@InjectMocks
	OwnerController controller;

	Set<Owner> owners;
	MockMvc mockMvc;

	@BeforeEach
	void setUp() throws Exception {
		owners = new HashSet<>();
		owners.add(Owner.builder().id(1L).build());
		owners.add(Owner.builder().id(2L).build());
		mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	}

	@Test
	void testFindOwners() throws Exception {
		when(ownerService.findAll()).thenReturn(owners);
		mockMvc.perform(get("/owners/")).andExpect(status().isOk()).andExpect(view().name("owners/index"));
	}
	@Test
	void findOwners() throws Exception {
		mockMvc.perform(get("/owners/find"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/findOwners"))
		.andExpect(model().attributeExists("owners"));
		
		verifyZeroInteractions(ownerService);
	}
	@Test
	void listownersByIndex() throws Exception{
		when(ownerService.findById(anyLong())).thenReturn( Owner.builder().id(1L).build());
		mockMvc.perform(get("/owners/1")
		).andExpect(status().isOk())
		.andExpect(view().name("owners/ownerDetails"))
		.andExpect(model().attribute("owner",isNotNull()));
	}
	@Test
	void processFindReturnOne()throws Exception {
		when(ownerService.findAllByLastNameLike(anyString()))
		.thenReturn(Arrays.asList(Owner.builder().id(1l).build()));
		
		mockMvc.perform(get("/owners"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"))
		/*.andExpect(model().attribute("owners",hasSize(2))*/;
	}
	@Test
	void processFindReturnMany()throws Exception {
		when(ownerService.findAllByLastNameLike(anyString()))
		.thenReturn(Arrays.asList(Owner.builder().id(1l).build(),Owner.builder().id(2l).build()));
		
		mockMvc.perform(get("/owners"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/ownersList"))
		.andExpect(model().attribute("selections",hasSize(2)));
		
	}
	@Test
	void displayOwner()throws Exception {

		when(ownerService.findById(anyLong()))
		.thenReturn(Owner.builder().id(1l).build());
		
		mockMvc.perform(get("/owners/123"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/ownerDetails"))
		.andExpect(model().attribute("owner", hasProperty("id")));
		
	} 
	@Test
	void initCreateForm()throws Exception {
		mockMvc.perform(get("/owners/new"))
		.andExpect(status().isOk())
		.andExpect(view().name("owners/createOrUpdateOwnerForm"))
		.andExpect(model().attributeExists("owner" ));
		verifyZeroInteractions(ownerService);
		
	}
	@Test 
	void processCreateForm()throws Exception{
		when(ownerService.save(ArgumentMatchers.any())).thenReturn(Owner.builder().id(1l).build());
		mockMvc.perform(post("/owners/new"))
		.andExpect(status().is3xxRedirection())
		.andExpect(view().name("redirect:/owners/1"))
		.andExpect(model().attributeExists("owner"));
		verify(ownerService).save(ArgumentMatchers.any());
	}
}
