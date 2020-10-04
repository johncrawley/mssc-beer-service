package com.jacstuff.msscbeerservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jacstuff.msscbeerservice.web.model.BeerDto;
import com.jacstuff.msscbeerservice.web.model.BeerStyle;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BeerServiceImpl implements BeerService {

	public BeerServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public BeerDto getBeerById(UUID beerId) {
		// TODO Auto-generated method stub
		return BeerDto.builder().id(beerId)
				.beerName("rough beer")
				.beerStyle(BeerStyle.LAGER)
				.build();
	}

	@Override
	public BeerDto saveBeer(BeerDto beerDto) {
		log.info("BeerServiceImpl Entered saveBeer() beer info: " + beerDto);
		BeerDto beerDto2 =  BeerDto.builder().id(UUID.randomUUID())
				.beerName(beerDto.getBeerName())
				.beerStyle(beerDto.getBeerStyle())
				.build();
		System.out.println("BeerServiceImpl saveBeer() beer id: " + beerDto2.getId().toString());
		return beerDto2;
	}


	@Override
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		//todo: impl
		
	}

	@Override
	public void deleteById(UUID beerId) {
		//todo: impl
		log.debug("deleting beer with id " + beerId.toString());
	}

}
