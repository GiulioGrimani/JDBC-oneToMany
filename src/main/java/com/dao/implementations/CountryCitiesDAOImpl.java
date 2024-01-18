package com.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dao.interfaces.CountryCitiesDAO;
import com.dao.interfaces.CountryCitiesSQL;
import com.dao.utils.ConnectionManager;
import com.model.dto.CityDTO;
import com.model.dto.CountryDTO;
import com.model.vo.CityVO;
import com.model.vo.CountryCitiesVO;
import com.model.vo.CountryVO;

public class CountryCitiesDAOImpl implements CountryCitiesDAO, CountryCitiesSQL {

	/*-
	 * E' in questo DAO che gestiamo manualmente la transazione poiche' vogliamo
	 * eseguire piu' di una operazione DML alla volta.
	 * 
	 * Step per eseguire una transazione:
	 * 
	 * 1. Aprire una connessione
	 * 2. Aprire una transazione impostando a false l'autocommit
	 * 3. Impostare il livello di isolamento desiderato (Serializable the best)
	 * 4. Svolgere le varie dml
	 * 5. eseguire il commit ed eventualmente il rollback
	 * 6. chiudere la connessione
	 */

	@Override
	public Integer insertCountryWithCity(CountryDTO countryDTO, CityDTO cityDTO) throws SQLException {

		Integer result = 0;

		// 1. Apertura connessione
		Connection connection = ConnectionManager.getConnection();

		// 2. Apertura transazione
		ConnectionManager.openTransaction(connection);

		// 3. Impostazione livello migliore di isolamento
		ConnectionManager.setBestTransactionIsolationLevel(connection);

		// 4. Esecuzione dml desiderate
		PreparedStatement countryPs = ConnectionManager.getPreparedStatementWithKeys(connection, INSERT_COUNTRY);

		countryPs.setString(1, countryDTO.getCountry());

		result += countryPs.executeUpdate();

		ResultSet countryKeySet = countryPs.getGeneratedKeys();

		Integer countryId = null;

		if (countryKeySet.next()) {
			countryId = countryKeySet.getInt(1);
		}

		PreparedStatement cityPs = ConnectionManager.getPreparedStatement(connection, INSERT_CITY);

		cityPs.setString(1, cityDTO.getCity());
		cityPs.setInt(2, countryId);

		result += cityPs.executeUpdate();

		try {
			// 5. Esecuzione commit
			ConnectionManager.doCommit(connection);
		} catch (SQLException sqle) {
			System.err.println("insertCountryWithCity transaction failed");
			// 5. Esecuzione eventuale rollback
			ConnectionManager.doRollback(connection);
		} finally {
			// 6. Chiusura connessione
			ConnectionManager.closeConnection(connection);
		}

		return result;
	}

	@Override
	public Integer insertCountryWithCities(CountryDTO countryDTO, List<CityDTO> citiesDTO) throws SQLException {

		Integer result = 0;

		Connection connection = ConnectionManager.getConnection();

		ConnectionManager.openTransaction(connection);

		ConnectionManager.setBestTransactionIsolationLevel(connection);

		PreparedStatement countryPs = ConnectionManager.getPreparedStatementWithKeys(connection, INSERT_COUNTRY);

		countryPs.setString(1, countryDTO.getCountry());

		result += countryPs.executeUpdate();

		ResultSet countryKeySet = countryPs.getGeneratedKeys();

		Integer countryId = null;

		if (countryKeySet.next()) {
			countryId = countryKeySet.getInt(1);
		}

		for (CityDTO cityDTO : citiesDTO) {
			PreparedStatement cityPs = ConnectionManager.getPreparedStatement(connection, INSERT_CITY);
			cityPs.setString(1, cityDTO.getCity());
			cityPs.setInt(2, countryId);
			result += cityPs.executeUpdate();
		}

		try {
			ConnectionManager.doCommit(connection);
		} catch (SQLException sqle) {
			System.err.println("insertCountryWithCities transaction failed");
			ConnectionManager.doRollback(connection);
		} finally {
			ConnectionManager.closeConnection(connection);
		}

		return result;
	}

	@Override
	public CountryCitiesVO readCitiesByCountry(Integer countryId) throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		PreparedStatement ps = ConnectionManager.getPreparedStatement(connection, READ_CITIES_BY_COUNTRY);

		ps.setInt(1, countryId);

		ResultSet rs = ConnectionManager.getResultSetOnPS(ps);

		CountryVO countryVO = null;
		List<CityVO> citiesVO = new ArrayList<>();

		while (rs.next()) {
			if (countryVO == null) {
				String country = rs.getString(2);
				Timestamp lastUpdate = rs.getTimestamp(3);
				countryVO = new CountryVO(countryId, country, lastUpdate);
			}
			CityVO cityVO = mapToCityVO(rs);
			cityVO.setCountryId(countryId);
			citiesVO.add(cityVO);
		}

		ConnectionManager.closeConnection(connection);

		return new CountryCitiesVO(countryVO, citiesVO);

	}

	private CityVO mapToCityVO(ResultSet rs) throws SQLException {
		Integer cityId = rs.getInt(4);
		String city = rs.getString(5);
		Timestamp lastUpdate = rs.getTimestamp(7);
		return new CityVO(cityId, city, null, lastUpdate);
	}

}
