package com.dao.interfaces;

public interface CitySQL {

	String INSERT_CITY = "INSERT INTO city(city, country_id) VALUES(?, ?);";

	String UPDATE_CITY = "UPDATE city SET city = ?, country_id = ? WHERE city_id = ?;";

	String DELETE_CITY = "DELETE FROM city WHERE city_id = ?;";

	String READ_CITIES = "SELECT * FROM city";

}
