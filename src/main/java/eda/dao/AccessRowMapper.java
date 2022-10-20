package eda.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;


public class AccessRowMapper  implements RowMapper<Timestamp>{

	@Override
	public Timestamp mapRow(ResultSet rs, int rowNum) throws SQLException {
		return rs.getTimestamp("last_accessed_at");
	}

}
