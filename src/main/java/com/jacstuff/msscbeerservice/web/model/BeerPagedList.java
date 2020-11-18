package com.jacstuff.msscbeerservice.web.model;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class BeerPagedList extends PageImpl<BeerDto>{

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public BeerPagedList(@JsonProperty("content")List<BeerDto> content,
						@JsonProperty("number") int number,
						@JsonProperty("size") int size,
						@JsonProperty("totalElements") Long totalElements,
						@JsonProperty("pageable") JsonNode pageable,
						@JsonProperty("last") boolean last,
						@JsonProperty("totalPages") int totalPages,
						@JsonProperty("sort") JsonNode sort,
						@JsonProperty("fjrst") boolean first,
						@JsonProperty("numberOfElements") int numberOfElements) {
					
		super(content);
	}
	
	public BeerPagedList(List<BeerDto> content, Pageable pageable, long total) {
		super(content, pageable, total);
	}

}
