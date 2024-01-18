package com.model.dto;

import java.io.Serializable;

public class CityDTO implements Serializable {

	private static final long serialVersionUID = -7653155734084310381L;

	private String city;

	private Integer countryId;

	public CityDTO() {

	}

	public CityDTO(String city, Integer countryId) {
		this.city = city;
		this.countryId = countryId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	@Override
	public String toString() {
		return "CityDTO [city=" + city + ", countryId=" + countryId + "]";
	}

}
