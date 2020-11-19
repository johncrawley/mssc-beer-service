package com.jacstuff.msscbeerservice.bootstrap;

import java.math.BigDecimal;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.jacstuff.msscbeerservice.domain.Beer;
import com.jacstuff.msscbeerservice.repositories.BeerRepository;

@Component
public class BeerLoader implements CommandLineRunner {

	private final BeerRepository beerRepository;

	public static final String BEER_1_UPC = "071231231231";
	public static final String BEER_2_UPC = "071231231232";
	public static final String BEER_3_UPC = "071231231233";
	
	public BeerLoader(BeerRepository beerRepository) {
		this.beerRepository = beerRepository;
	}

	
	@Override
	public void run(String... args) throws Exception {
		loadBeerObjects();
	}
	
	
	private void loadBeerObjects() {
		if(beerRepository.count() != 0) {
			System.out.println("BeerLoader, repository already loaded :" + beerRepository.count());
			return;
		}
		
		beerRepository.save(Beer.builder()
				.beerName("Longfield")
				.beerStyle("IPA")
				.quantityToBrew(200)
				.upc(BEER_1_UPC)
				.price(new BigDecimal("5.99"))
				.build());
		
		beerRepository.save(Beer.builder()
				.beerName("Spicy Dragon")
				.beerStyle("Ale")
				.quantityToBrew(140)
				.upc(BEER_2_UPC)
				.price(new BigDecimal("3.59"))
				.build());

		System.out.println("BeerLoader, loaded beers :" + beerRepository.count());
	}
}
