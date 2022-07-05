package eda.dao;

import java.sql.Types;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import eda.dto.Data;

@Repository
public class DataDAOImpl implements DataDAO {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public Data getData(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveData(Data data) {
		String query = "INSERT INTO data (file_name,file_url,owner_id,file_size,hash) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(query,data.getFileName(),data.getFileUrl(),data.getOwnerId(),data.getFileSize(),data.getHash());
	}

	@Override
	public boolean shareData(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeData(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean contains(long hash) {
		String query = "SELECT * from data WHERE hash = ?";
		Object[] args = new Object[] {hash};
		int[] argsType = new int[] {Types.BIGINT};
		List<Data> result = jdbcTemplate.query(query, args,argsType,new DataRowMapperImpl());
		if(result.size()>0) {
			return true;
		}
		return false;
	}
	
}
