package com.service.implementations;

import java.sql.SQLException;
import java.util.List;

import com.dao.implementations.CityDAOImpl;
import com.dao.interfaces.CityDAO;
import com.model.dto.CityDTO;
import com.model.vo.CityVO;
import com.service.interfaces.CityService;

public class CityServiceImpl implements CityService {

	private CityDAO dao = new CityDAOImpl();

	@Override
	public Integer insertCity(CityDTO cityDTO) {
		if (cityDTO == null || cityDTO.getCountryId() == null || cityDTO.getCountryId() < 1
				|| cityDTO.getCity() == null) {
			throw new IllegalArgumentException("insertCity fallita: argomento non valido");
		}
		Integer result = null;
		try {
			result = dao.insertCity(cityDTO);
			if (result != 1) {
				throw new SQLException("insertCity fallita: dml non eseguita correttamente");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer updateCity(Integer cityId, CityDTO cityDTO) {
		if (cityId == null || cityId < 1 || cityDTO == null || cityDTO.getCity() == null
				|| cityDTO.getCountryId() == null || cityDTO.getCountryId() < 1) {
			throw new IllegalArgumentException("updateCity fallita: argomento non valido");
		}

		Integer result = null;
		try {
			result = dao.updateCity(cityId, cityDTO);
			if (result != 1) {
				throw new SQLException("updateCity fallita: dml non eseguita correttamente");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer deleteCity(Integer cityId) {
		if (cityId == null || cityId < 1) {
			throw new IllegalArgumentException("deleteCity fallita: argomento non valido");
		}

		Integer result = null;
		try {
			result = dao.deleteCity(cityId);
			if (result != 1) {
				throw new SQLException("deleteCity fallita: dml non eseguita correttamente");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CityVO> readCities() {
		List<CityVO> result = null;

		try {
			result = dao.readCities();
		} catch (SQLException sqle) {
			System.err.println("readCities fallita: query non eseguita correttamente");
			sqle.printStackTrace();
		}
		return result;
	}

}
