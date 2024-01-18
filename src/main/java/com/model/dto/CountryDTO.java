package com.model.dto;

import java.io.Serializable;

public class CountryDTO implements Serializable {

	private static final long serialVersionUID = -2703302245353250869L;

	private String country;

	public CountryDTO() {

	}

	public CountryDTO(String country) {
		this.country = country;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "CountryDTO [country=" + country + "]";
	}

}
