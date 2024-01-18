package com.service.interfaces;

import java.util.List;

import com.model.dto.CountryDTO;
import com.model.vo.CountryVO;

public interface CountryService {

	Integer insertCountry(CountryDTO countryDTO);

	Integer updateCountry(Integer countryId, CountryDTO countryDTO);

	Integer deleteCountry(Integer countryId);

	List<CountryVO> readCountries();

}
