package com.jacstuff.msscbeerservice.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacstuff.msscbeerservice.services.BeerService;
import com.jacstuff.msscbeerservice.web.model.BeerDto;
import com.jacstuff.msscbeerservice.web.model.BeerStyle;

@WebMvcTest(BeerController.class)
public class BeerControllerTest {
	
	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;
	@MockBean BeerService beerService;
	BeerDto validBeer;
	
	@BeforeEach
	void setup() {
		validBeer = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("Goodbeer v3")
				.beerStyle(BeerStyle.PALE_ALE)
				.upc(12312331L)
				.build();
	}
	
	
	
	
	@Test
	void getBeerById() throws Exception{
		mockMvc.perform(
				get("/api/v1/beer/" + UUID.randomUUID().toString())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto = BeerDto.builder().beerName("Genuine Class").beerStyle(BeerStyle.PORTER).build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(post("/api/v1/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isCreated());//.andExpect(header().string("Location", "/api/v1/beer/1"));
	}
	
	@Test
	void updateBeerById() throws Exception {

		BeerDto beerDto = validBeer;
		beerDto.setId(null);
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isNoContent());
	}

	
	
}
