package com.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.model.dto.CountryDTO;
import com.model.vo.CountryVO;

public interface CountryDAO {

	Integer insertCountry(CountryDTO countryDTO) throws SQLException;

	Integer updateCountry(Integer countryId, CountryDTO countryDTO) throws SQLException;

	Integer deleteCountry(Integer countryId) throws SQLException;

	List<CountryVO> readCountries() throws SQLException;
}
