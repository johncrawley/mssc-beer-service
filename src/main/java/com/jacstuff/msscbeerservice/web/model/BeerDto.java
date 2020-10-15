package com.jacstuff.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // provides getters, setters, equals and hashcode
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BeerDto {

	@Null
	private UUID id;
	
	@NotBlank
	private String beerName;
	
	@NotNull
	private BeerStyle beerStyle;
	
	@Positive
	private Long upc;
	
	@Null
	private Integer version;
	
	@Null
	private OffsetDateTime createdDate;
	
	@Null
	private OffsetDateTime	modifiedDate;

	@NotNull
	@Positive
	private BigDecimal price;
	private Integer quantityOnHand;
	
}
