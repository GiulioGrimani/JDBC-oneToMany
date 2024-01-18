package com.model.vo;

import java.io.Serializable;
import java.util.List;

public class CountryCitiesVO implements Serializable {

	private static final long serialVersionUID = 7508679686903161377L;

	private CountryVO country;

	private List<CityVO> cities;

	public CountryCitiesVO() {

	}

	public CountryCitiesVO(CountryVO country, List<CityVO> cities) {
		this.country = country;
		this.cities = cities;
	}

	public CountryVO getCountry() {
		return country;
	}

	public void setCountry(CountryVO country) {
		this.country = country;
	}

	public List<CityVO> getCities() {
		return cities;
	}

	public void setCities(List<CityVO> cities) {
		this.cities = cities;
	}

}
