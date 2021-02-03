package org.mycompany.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class StarShipPojo {

	@ApiModelProperty(example="TIE Advanced x1")
	@JsonProperty
	private String name;
	
	@ApiModelProperty(example="Twin Ion Engine Advanced x1")
	@JsonProperty
	private String model;
	
	public StarShipPojo() {
	}
	
	public StarShipPojo(String name, String model) {
		this.name = name;
		this.model = model;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getModel() {
		return model;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		
		int hash = 1;
		hash = prime * hash  + ((name == null) ? 0 : name.hashCode());
		hash = prime * hash  + ((model == null) ? 0 : model.hashCode());
		
		return hash;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		
		if (o == null || getClass() != o.getClass()) return false;

		StarShipPojo that = (StarShipPojo) o;

		return (name != null ? name.equals(that.name) : that.name == null) &&
				(model != null ? model.equals(that.model) : that.model == null);
	}

	@Override
	public String toString() {
		return "StarShip {" +
				"name='" + name + '\'' +
				", model=" + model + 
				'}';
	}
	
}
