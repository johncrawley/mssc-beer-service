package com.jacstuff.msscbeerservice.web.controller;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.put;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jacstuff.msscbeerservice.services.BeerService;
import com.jacstuff.msscbeerservice.web.model.BeerDto;
import com.jacstuff.msscbeerservice.web.model.BeerStyle;

@AutoConfigureRestDocs(uriScheme ="http", uriHost ="localhost", uriPort=8090)
@WebMvcTest(BeerController.class)
@ExtendWith(RestDocumentationExtension.class)
public class BeerControllerTest {
	
	@Autowired MockMvc mockMvc;
	@Autowired ObjectMapper objectMapper;
	@MockBean BeerService beerService;
	BeerDto validBeer;
	
	@BeforeEach
	void setup() {
		validBeer = BeerDto.builder()
				.id(UUID.randomUUID())
				.beerName("Goodbeer 3")
				.beerStyle(BeerStyle.PALE_ALE)
				.upc(12312331L)
				.build();
	}
	
	
	@Test
	void getBeerById() throws Exception{
		mockMvc.perform(
				get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
				.param("iscold", "yes") // not a real param, will be ignored by controller, just showing and example of how to document path params below
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
		
				.andDo(document("v1/beer-get", 
						pathParameters(
								parameterWithName("beerId").description("UUID of desired beer to get.")
						),
						requestParameters(
								parameterWithName("iscold").description("Is Beer Cold query parameter.")
						),
						responseFields(
								fieldWithPath("id").description("Id of Beer").type(UUID.class),
								fieldWithPath("beerName").description("The name of the Beer"),
								fieldWithPath("beerStyle").description("What kind of Beer it is"),
								fieldWithPath("upc").description("A upc of Beer"),
								fieldWithPath("version").description("what version of Beer"),
								fieldWithPath("createdDate").description("The date the beer was added"),
								fieldWithPath("modifiedDate").description("The most recent date the beer data was modified"),
								fieldWithPath("price").description("Price of Beer"),
								fieldWithPath("quantityOnHand").description("Number of beer stock currently available")
						)
						
					)
				);
	}
	
	@Test
	void saveNewBeer() throws Exception {
		BeerDto beerDto = BeerDto.builder().beerName("Genuine Class").beerStyle(BeerStyle.PORTER).build();
		String beerDtoJson = objectMapper.writeValueAsString(beerDto);
		
		ConstrainedFields fields = new ConstrainedFields(BeerDto.class);
		
		mockMvc.perform(post("/api/v1/beer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(beerDtoJson))
				.andExpect(status().isCreated()) //.andExpect(header().string("Location", "/api/v1/beer/1"));
				.andDo(document("v1/beer-new", 
					PayloadDocumentation.requestFields(
							fields.withPath("beerName").description("Name of beer"),
							fields.withPath("price").description("Price of beer"),
							fields.withPath("beerStyle").description("Style of beer"),
							fields.withPath("upc").description("Beer UPC"),
							fields.withPath("id").ignored(),
							fields.withPath("version").ignored(),
							fields.withPath("createdDate").ignored(),
							fields.withPath("modifiedDate").ignored(),
							fields.withPath("quantityOnHand").ignored()
					)	
						
				));
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

	
	private static class ConstrainedFields {
		
		private final ConstraintDescriptions constraintDescriptions;
		
		ConstrainedFields(Class<?> input){
			this.constraintDescriptions = new ConstraintDescriptions(input);
		}
		
		private FieldDescriptor withPath(String path) {
			return fieldWithPath(path).attributes(key("constraints")
					.value(StringUtils.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
		}
	}
}
