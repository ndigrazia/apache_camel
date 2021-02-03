package org.mycompany.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class CharacterPojo {

	@ApiModelProperty(example="0teUVBC5WU")
	@JsonProperty
	private String id;

	@ApiModelProperty(example="Darth Vader")
	@JsonProperty
	private String name;
	
	@ApiModelProperty(example="202")
	@JsonProperty
	private int height;
	
	@ApiModelProperty(example="Tatooine")
	@JsonProperty
	private String homeworld;
	
	@ApiModelProperty(readOnly=true, example="2016-01-31 18:00:00")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt = LocalDateTime.now();

	@ApiModelProperty(value = "starships")
	@JsonProperty
	private List<StarShipPojo> starships = new ArrayList<StarShipPojo>(); 
	
	public CharacterPojo() {
	}

	public CharacterPojo(String id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public CharacterPojo(String id, String name, int height) {
		this.id = id;
		this.name = name;
		this.height = height;
	}
	
	public CharacterPojo(String id, String name, int height, String homeworld) {
		this.id = id;
		this.name = name;
		this.height = height;
		this.homeworld = homeworld;
	}
	
	public String getHomeworld() {
		return homeworld;
	}

	public void setHomeworld(String homeworld) {
		this.homeworld = homeworld;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getId() {
		return id;
	}
	
	@JsonIgnore
	public void setId(String id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	public List<StarShipPojo> getStarships() {
		return starships;
	}

	public void setStarships(List<StarShipPojo> starships) {
		this.starships = starships;
	}

	public void addStarship(StarShipPojo starship) {
		starships.add(starship);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		
		int hash = 1;
		hash = prime * hash + ((id == null) ? 0 : id.hashCode());
		hash = prime * hash  + ((name == null) ? 0 : name.hashCode());
		hash = prime * hash  + ((homeworld == null) ? 0 : homeworld.hashCode());
		hash = prime * hash + height;
		hash = prime * hash  + ((starships == null) ? 0 : starships.hashCode());
		
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		
		if (o == null || getClass() != o.getClass()) return false;

		CharacterPojo that = (CharacterPojo) o;

		return (id != null ? id.equals(that.id) : that.id == null) && 
				(name != null ? name.equals(that.name) : that.name == null) &&
				(homeworld != null ? homeworld.equals(that.homeworld) : that.homeworld == null) &&
				height == that.height && starships.equals(that.starships);
	}

	@Override
	public String toString() {
		return "Character {" +
				"id='" + id + '\'' +
				", name=" + name + 
				", height=" + height +
				", homeworld=" + homeworld +
				", starships=" + starships +
				", createdLocalDate=" + createdAt +
				'}';
	}
	
}
