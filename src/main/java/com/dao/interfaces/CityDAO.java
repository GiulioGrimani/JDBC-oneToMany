package com.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.model.dto.CityDTO;
import com.model.vo.CityVO;

public interface CityDAO {

	// Quando il Country di riferimento e' gia' presente sul DB
	Integer insertCity(CityDTO cityDTO) throws SQLException;

	// Quando il Country di riferimento e' gia' presente sul DB
	Integer updateCity(Integer cityId, CityDTO cityDTO) throws SQLException;

	Integer deleteCity(Integer cityId) throws SQLException;

	List<CityVO> readCities() throws SQLException;

}
