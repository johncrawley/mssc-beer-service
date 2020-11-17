package com.jacstuff.msscbeerservice.web.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@JsonTest
public class BeerTest extends BaseTest {

	@Autowired ObjectMapper objectMapper;
	
	public BeerTest() {
		// TODO Auto-generated constructor stub
	}
	
	@Test
	void testSerializeDto() throws JsonProcessingException {
		
		BeerDto beerDto = getDto();
		String jsonString = objectMapper.writeValueAsString(beerDto);
		System.out.println(jsonString);
	}
	
	
	@Test
	void testDeserializeDto() throws JsonProcessingException {
		String jsonData = "{\"beerName\":\"GreatBeer\",\"beerStyle\":\"LAGER\",\"upc\":123123123,\"version\":null,\"createdDate\":\"2020-11-17T21-14-46+0000\",\"modifiedDate\":null,\"price\":8.99,\"quantityOnHand\":null,\"myLocalDate\":\"20201117\",\"beerId\":null}";
		BeerDto beerDto = objectMapper.readValue(jsonData, BeerDto.class);
		System.out.println(beerDto);
	}

}
