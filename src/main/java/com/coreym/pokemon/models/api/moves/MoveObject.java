package com.coreym.pokemon.models.api.moves;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MoveObject {
	
	@JsonProperty("move")
	private Move move;

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}
	
	

}
