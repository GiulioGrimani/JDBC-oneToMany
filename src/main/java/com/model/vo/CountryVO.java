package com.model.vo;

import java.io.Serializable;
import java.sql.Timestamp;

public class CountryVO implements Serializable {

	private static final long serialVersionUID = -3170162841276764862L;

	private Integer countryId;

	private String country;

	private Timestamp lastUpdate;

	public CountryVO() {

	}

	public CountryVO(Integer countryId, String country, Timestamp lastUpdate) {
		this.countryId = countryId;
		this.country = country;
		this.lastUpdate = lastUpdate;
	}

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Timestamp getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Timestamp lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	@Override
	public String toString() {
		return "CountryVO [countryId=" + countryId + ", country=" + country + ", lastUpdate=" + lastUpdate + "]";
	}

}
