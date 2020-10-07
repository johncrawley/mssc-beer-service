package com.jacstuff.msscbeerservice.repositories;

import java.util.UUID;

import com.jacstuff.msscbeerservice.domain.Beer;
import org.springframework.data.repository.PagingAndSortingRepository;

// don't need to annote this as a repository, Spring Data will automatically pick it up
public interface BeerRepository extends PagingAndSortingRepository<Beer, UUID> {



}
