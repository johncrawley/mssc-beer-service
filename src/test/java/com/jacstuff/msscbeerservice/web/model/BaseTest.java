package com.jacstuff.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;

public class BaseTest {

	BeerDto getDto() {
		return BeerDto.builder()
				.beerName("GreatBeer")
				.beerStyle(BeerStyle.LAGER)
				.price(new BigDecimal("8.99"))
				.upc(123123123L)
				.createdDate(OffsetDateTime.now())
				.myLocalDate(LocalDate.now())
				.build();
			
	}

}
