package com.dao.implementations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dao.interfaces.CityDAO;
import com.dao.interfaces.CitySQL;
import com.dao.utils.ConnectionManager;
import com.model.dto.CityDTO;
import com.model.vo.CityVO;

public class CityDAOImpl implements CityDAO, CitySQL {

	/*
	 * Dal momento che stiamo eseguendo una singola DML, essa viene gia' eseguita
	 * all'interno di un contesto transazionale, pertanto non c'e' bisogno della
	 * gestione manuale della transazione. Dalla documentazione:
	 * "When a connection is created, it is in auto-commit mode"
	 */
	@Override
	public Integer insertCity(CityDTO cityDTO) throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		PreparedStatement ps = ConnectionManager.getPreparedStatement(connection, INSERT_CITY);

		ps.setString(1, cityDTO.getCity());
		ps.setInt(2, cityDTO.getCountryId());

		Integer result = ps.executeUpdate();

		ConnectionManager.closeConnection(connection);

		return result;
	}

	@Override
	public Integer updateCity(Integer cityId, CityDTO cityDTO) throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		PreparedStatement ps = ConnectionManager.getPreparedStatement(connection, UPDATE_CITY);

		ps.setString(1, cityDTO.getCity());
		ps.setInt(2, cityDTO.getCountryId());
		ps.setInt(3, cityId);

		Integer result = ps.executeUpdate();

		ConnectionManager.closeConnection(connection);

		return result;
	}

	@Override
	public Integer deleteCity(Integer cityId) throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		PreparedStatement ps = ConnectionManager.getPreparedStatement(connection, DELETE_CITY);

		ps.setInt(1, cityId);

		Integer result = ps.executeUpdate();

		ConnectionManager.closeConnection(connection);

		return result;
	}

	@Override
	public List<CityVO> readCities() throws SQLException {

		Connection connection = ConnectionManager.getConnection();

		ResultSet rs = ConnectionManager.getResultSetOnSt(connection, READ_CITIES);

		List<CityVO> result = new ArrayList<>();

		while (rs.next()) {
			CityVO cityVO = mapToCityVO(rs);
			result.add(cityVO);
		}

		ConnectionManager.closeConnection(connection);

		return result;
	}

	private CityVO mapToCityVO(ResultSet rs) throws SQLException {
		Integer cityId = rs.getInt("city_id");
		String city = rs.getString("city");
		Integer countryId = rs.getInt("country_id");
		Timestamp lastUpdate = rs.getTimestamp("last_update");
		return new CityVO(cityId, city, countryId, lastUpdate);
	}

}
