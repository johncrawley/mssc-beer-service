package com.jacstuff.msscbeerservice.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jacstuff.msscbeerservice.services.BeerService;
import com.jacstuff.msscbeerservice.services.BeerServiceImpl;
import com.jacstuff.msscbeerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
	
	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId){
		return new ResponseEntity<>(BeerDto.builder().build(), HttpStatus.OK);
	}

	@PostMapping()
	public ResponseEntity<BeerDto> saveNewBeer(@RequestBody BeerDto beerDto) {	
		System.out.println("saved beer! "  + beerDto.getBeerName());
		BeerDto savedDto = beerService.saveBeer(beerDto);
		HttpHeaders headers = new HttpHeaders();
		if(savedDto != null) {
			headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
		}
	return new ResponseEntity<>(beerDto, headers, HttpStatus.CREATED);
	}


	@PutMapping("/{beerId}")
	public ResponseEntity<Void> updateBeerById(@PathVariable UUID beerId, @RequestBody BeerDto beerDto) {
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
}
