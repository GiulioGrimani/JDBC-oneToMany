package com.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CityVO implements Serializable {

	private static final long serialVersionUID = -1756365087988896647L;

	private Integer cityId;

	private String city;

	private Integer countryId;

	private Timestamp lastUpdate;

	public CityVO() {

	}

	public CityVO(Integer cityId, String city, Integer countryId, Timestamp lastUpdate) {
		this.cityId = cityId;
		this.city = city;
		this.countryId = countryId;
		this.lastUpdate = lastUpdate;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
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

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "CityVO [cityId=" + cityId + ", city=" + city + ", countryId=" + countryId + ", lastUpdate=" + lastUpdate
				+ "]";
	}

}
