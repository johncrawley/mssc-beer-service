package com.jacstuff.msscbeerservice.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.jacstuff.msscbeerservice.domain.Beer;
import com.jacstuff.msscbeerservice.repositories.BeerRepository;
import com.jacstuff.msscbeerservice.web.mappers.BeerMapper;
import com.jacstuff.msscbeerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class BeerServiceImpl implements BeerService {
	
	private final BeerRepository beerRepository;
	private final BeerMapper beerMapper;


	@Override
	public BeerDto getBeerById(UUID beerId) {
		
		return beerMapper.beerToBeerDto( beerRepository.findById(beerId).orElseThrow(NotFoundException::new));
	}

	@Override
	public BeerDto saveBeer(BeerDto beerDto) {
		// the beer repository takes in a Beer object, so using the mapper to convert BeerDto to Beer
		// we return a BeerDto, so using the mapper again to convert the Beer entity returned by repository to BeerDto
		Beer beer = beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
		return beerMapper.beerToBeerDto(beer);
	}


	@Override
	public BeerDto updateBeer(UUID beerId, BeerDto beerDto) {
		Beer beer = beerRepository.findById(beerId).orElseThrow(NotFoundException::new);
		beer.setBeerName(beerDto.getBeerName());
		beer.setBeerStyle(beerDto.getBeerStyle().name());
		beer.setPrice(beerDto.getPrice());
		beer.setUpc(beerDto.getUpc());
		Beer updatedBeer = beerRepository.save(beer);
		return beerMapper.beerToBeerDto(updatedBeer);
	}

	@Override
	public void deleteById(UUID beerId) {
		//todo: impl
		log.debug("deleting beer with id " + beerId.toString());
	}

}
