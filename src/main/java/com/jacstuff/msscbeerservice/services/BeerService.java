package com.jacstuff.msscbeerservice.services;

import java.util.UUID;

import com.jacstuff.msscbeerservice.web.model.BeerDto;


public interface BeerService {
	public BeerDto getBeerById(UUID beerId);
	public BeerDto saveBeer(BeerDto beerDto);
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto);
	public void deleteById(UUID beerId);
}
