package com.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dao.interfaces.CountryDAO;
import com.dao.interfaces.CountrySQL;
import com.dao.utils.ConnectionManager;
import com.model.dto.CountryDTO;
import com.model.vo.CountryVO;

public class CountryDAOImpl implements CountryDAO, CountrySQL {

	/*
	 * Dal momento che stiamo eseguendo una singola DML, essa viene gia' eseguita
	 * all'interno di un contesto transazionale, pertanto non c'e' bisogno della
	 * gestione manuale della transazione. Dalla documentazione:
	 * "When a connection is created, it is in auto-commit mode"
	 */
	@Override
	public Integer insertCountry(CountryDTO countryDTO) throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		PreparedStatement ps = ConnectionManager.getPreparedStatement(connection, INSERT_COUNTRY);

		ps.setString(1, countryDTO.getCountry());

		Integer result = ConnectionManager.executeSqlOnPs(ps);

		ConnectionManager.closeConnection(connection);

		return result;
	}

	@Override
	public Integer updateCountry(Integer countryId, CountryDTO countryDTO) throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		PreparedStatement ps = ConnectionManager.getPreparedStatement(connection, UPDATE_COUNTRY);

		ps.setString(1, countryDTO.getCountry());
		ps.setInt(2, countryId);

		Integer result = ConnectionManager.executeSqlOnPs(ps);

		ConnectionManager.closeConnection(connection);

		return result;
	}

	/*
	 * Attenzione: se voglio eliminare un Country, devo prima eliminare tutte le
	 * Cities afferenti: non posso eliminare un record parent se e' referenziato da
	 * almeno un record child. Quindi, in base a come dobbiamo interpretare il
	 * metodo, potrebbe essere opportuno gestire manualmente la transazione. In
	 * questo caso cancelliamo un Country presupponendo che non sia referenziato da
	 * alcuna City (poiche' la gestione manuale della transazione la facciamo vedere
	 * nel CountryCitiesDAOImpl)
	 */
	@Override
	public Integer deleteCountry(Integer countryId) throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		PreparedStatement ps = ConnectionManager.getPreparedStatement(connection, DELETE_COUNTRY);

		ps.setInt(1, countryId);

		Integer result = ConnectionManager.executeSqlOnPs(ps);

		ConnectionManager.closeConnection(connection);

		return result;
	}

	@Override
	public List<CountryVO> readCountries() throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		ResultSet rs = ConnectionManager.getResultSetOnSt(connection, READ_COUNTRIES);

		List<CountryVO> result = new ArrayList<>();

		while (rs.next()) {
			CountryVO countryVO = mapToCountryVO(rs);
			result.add(countryVO);
		}

		ConnectionManager.closeConnection(connection);

		return result;
	}

	private CountryVO mapToCountryVO(ResultSet rs) throws SQLException {
		Integer countryId = rs.getInt("country_id");
		String country = rs.getString("country");
		Timestamp lastUpdate = rs.getTimestamp("last_update");
		return new CountryVO(countryId, country, lastUpdate);
	}

}
