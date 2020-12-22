package com.jacstuff.msscbeerservice.web.controller;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.jacstuff.msscbeerservice.services.BeerService;
import com.jacstuff.msscbeerservice.web.model.BeerDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // the beer service field will automatically get wired into the generated constructor (if marked final)
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {
	
	private final BeerService beerService;

	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeerById(@PathVariable UUID beerId){
		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
		
	}

	@PostMapping()
	public ResponseEntity<BeerDto> saveNewBeer(@Valid @RequestBody BeerDto beerDto) {	

		BeerDto savedDto = beerService.saveBeer(beerDto);
		HttpHeaders headers = new HttpHeaders();
		if(savedDto != null) {
			headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());
		}
	return new ResponseEntity<>(savedDto, headers, HttpStatus.CREATED);
	}


	@PutMapping("/{beerId}")
	public ResponseEntity<BeerDto> updateBeerById(@PathVariable UUID beerId, @Valid @RequestBody BeerDto beerDto) {
		BeerDto updatedBeer = beerService.updateBeer(beerId, beerDto);
		return new ResponseEntity<>(updatedBeer, HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping({"/{beerId}"})
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteById(beerId);
	}
}
