package com.coreym.pokemon.models.pokemon;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.coreym.pokemon.models.api.images.PokemonImagesList;
import com.coreym.pokemon.models.api.moves.MoveObject;
import com.coreym.pokemon.models.api.stats.PokemonStat;
import com.coreym.pokemon.models.api.types.TypeObject;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity()
@Table(name = "pokemon")
public class Pokemon {

	@Id
	private Long id;

	private String name;

	private Double hp;

	private Double attack;

	private Double defense;

	private Double specialAttack;

	private Double specialDefense;

	private Double speed;

	@Transient
	@JsonProperty("types")
	private List<TypeObject> types;

	@Transient
	@JsonProperty("moves")
	private List<MoveObject> moveList;
//	
	@Transient
	@JsonProperty("sprites")
	private PokemonImagesList sprites;
//	
	@Transient
	@JsonProperty("stats")
	private List<PokemonStat> stats;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	public Pokemon() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	
//	
//
	public List<TypeObject> getTypes() {
		return types;
	}

	public void setTypes(List<TypeObject> types) {
		this.types = types;
	}

//
	public List<MoveObject> getMoveList() {
		return moveList;
	}

	public void setMoveList(List<MoveObject> moveList) {
		this.moveList = moveList;
	}

//
	public PokemonImagesList getSprites() {
		return sprites;
	}

	public void setSprites(PokemonImagesList sprites) {
		this.sprites = sprites;
	}

	public List<PokemonStat> getStats() {
		return stats;
	}

	public void setStats(List<PokemonStat> stats) {
		this.stats = stats;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Double getHp() {
		return hp;
	}

	public void setHp(Double hp) {
		this.hp = hp;
	}

	public Double getAttack() {
		return attack;
	}

	public void setAttack(Double attack) {
		this.attack = attack;
	}

	public Double getDefense() {
		return defense;
	}

	public void setDefense(Double defense) {
		this.defense = defense;
	}

	public Double getSpecialAttack() {
		return specialAttack;
	}

	public void setSpecialAttack(Double specialAttack) {
		this.specialAttack = specialAttack;
	}

	public Double getSpecialDefense() {
		return specialDefense;
	}

	public void setSpecialDefense(Double specialDefense) {
		this.specialDefense = specialDefense;
	}

	public Double getSpeed() {
		return speed;
	}

	public void setSpeed(Double speed) {
		this.speed = speed;
	}

	public void setStatBlock() {
		
		if (this.getStats() != null) {
			
			for (PokemonStat pStat : this.getStats()) {
				switch (pStat.getStatInfo().getName()) {
				case "hp":
					this.setHp(pStat.getBaseStat());
					break;
				case "attack":
					this.setAttack(pStat.getBaseStat());
					break;
				case "defense":
					this.setDefense(pStat.getBaseStat());
					break;
				case "speed":
					this.setSpeed(pStat.getBaseStat());
					break;
				case "special-attack":
					this.setSpecialAttack(pStat.getBaseStat());
					break;
				case "special-defense":
					this.setSpecialDefense(pStat.getBaseStat());
					break;
				default:
					System.out.println("No stat found");
					break;
					
				}
			}
		}


	}

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

}
