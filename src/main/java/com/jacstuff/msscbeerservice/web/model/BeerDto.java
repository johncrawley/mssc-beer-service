package com.jacstuff.msscbeerservice.web.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
	@JsonProperty("beerId")
	private UUID id;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String beerName;
	
	@NotNull
	private BeerStyle beerStyle;
	
	@NotNull
	private String upc;
	
	@Null
	private Integer version;
	
	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH-mm-ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime createdDate;
	
	@Null
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH-mm-ssZ", shape = JsonFormat.Shape.STRING)
	private OffsetDateTime	modifiedDate;

	@NotNull
	@Positive
	@JsonFormat(shape=Shape.STRING)
	private BigDecimal price;
	
	private Integer quantityOnHand;
	
	@Null
	@JsonSerialize(using = LocalDateSerializer.class)
	@JsonDeserialize(using = LocalDateDeserializer.class)
	private LocalDate myLocalDate;
	
}
