package eda.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import eda.dto.Data;

public class DataRowMapperImpl implements RowMapper<Data>{

	@Override
	public Data mapRow(ResultSet rs, int rowNum) throws SQLException {
		Data data = new Data();
		data.setId(rs.getInt("d_id"));
		data.setFileName(rs.getString("file_name"));
		data.setFileUrl(rs.getString("file_url"));
		data.setOwnerId(rs.getInt("owner_id"));
		data.setFileSize(rs.getLong("file_size"));
		data.setCreatedTime(rs.getTimestamp("created_time"));
		data.setHash(rs.getInt("hash"));
		return data;
	}

}
