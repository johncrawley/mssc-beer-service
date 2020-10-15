package com.jacstuff.msscbeerservice.web.mappers;

import org.mapstruct.Mapper;

import com.jacstuff.msscbeerservice.domain.Beer;
import com.jacstuff.msscbeerservice.web.model.BeerDto;

@Mapper(uses= {DateMapper.class})
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);
	
	Beer beerDtoToBeer(BeerDto beerDto);
}
