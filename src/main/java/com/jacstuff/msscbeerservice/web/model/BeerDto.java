package com.jacstuff.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // provides getters, setters, equals and hashcode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

	private UUID id;
	private Integer version;
	private OffsetDateTime createdDate;
	private OffsetDateTime	modifiedDate;
	private String beerName;
	private BeerStyle beerStyle;
	
	private Long upc;
	private BigDecimal price;
	private Integer quantityOnHand;
	
}
