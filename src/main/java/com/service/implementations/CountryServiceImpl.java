package com.service.implementations;

import java.sql.SQLException;
import java.util.List;

import com.dao.implementations.CountryDAOImpl;
import com.dao.interfaces.CountryDAO;
import com.model.dto.CountryDTO;
import com.model.vo.CountryVO;
import com.service.interfaces.CountryService;

public class CountryServiceImpl implements CountryService {

	private CountryDAO dao = new CountryDAOImpl();

	@Override
	public Integer insertCountry(CountryDTO countryDTO) {
		if (countryDTO == null || countryDTO.getCountry() == null) {
			throw new IllegalArgumentException("insertCountry fallita: argomento non valido");
		}
		Integer result = null;
		try {
			result = dao.insertCountry(countryDTO);

			if (result != 1) {
				throw new SQLException("insertCountry fallita: dml non eseguita correttamente");
			}

		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer updateCountry(Integer countryId, CountryDTO countryDTO) {
		if (countryId == null || countryId < 1 || countryDTO == null || countryDTO.getCountry() == null) {
			throw new IllegalArgumentException("updateCountry fallita: argomento non valido");
		}
		Integer result = null;
		try {
			result = dao.updateCountry(countryId, countryDTO);

			if (result != 1) {
				throw new SQLException("updateCountry fallita: dml non eseguita correttamente");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer deleteCountry(Integer countryId) {
		if (countryId == null || countryId < 1) {
			throw new IllegalArgumentException("deleteCountry fallita: argomento non valido");
		}
		Integer result = null;
		try {
			result = dao.deleteCountry(countryId);
			if (result != 1) {
				throw new SQLException("deleteCountry fallita: dml non eseguita correttamente");
			}
		} catch (SQLException sqle) {
			System.err.println(sqle.getMessage());
			sqle.printStackTrace();
		}
		return result;
	}

	@Override
	public List<CountryVO> readCountries() {

		List<CountryVO> result = null;

		try {
			result = dao.readCountries();
		} catch (SQLException sqle) {
			System.err.println("readCountries fallita: query non eseguita correttamente");
			sqle.printStackTrace();
		}
		return result;

	}

}
