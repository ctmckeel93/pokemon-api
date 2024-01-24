package com.coreym.pokemon.models.api.types;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TypeObject {
	
	@JsonProperty("type")
	private TypeInfo info;

	public TypeInfo getInfo() {
		return info;
	}

	public void setInfo(TypeInfo info) {
		this.info = info;
	}
	
	
	
	
	
	

}
