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
	public void saveData(Data data) {
		String query = "INSERT INTO data (file_name,file_url,owner_id,file_size,hash) VALUES (?,?,?,?,?)";
		jdbcTemplate.update(query,data.getFileName(),data.getFileUrl(),data.getOwnerId(),data.getFileSize(),data.getHash());
		Data write = getData(data.getHash(),data.getOwnerId());
		if(write == null) {
			return;
		}
		String accessQuery = "INSERT INTO access (viewer_id,d_id) VALUES (?,?)";
		jdbcTemplate.update(accessQuery,write.getOwnerId(),write.getId());
	}

	@Override
	public void shareData(int viewerId,int dataId) {
		String query = "INSERT INTO access (viewer_id,d_id) VALUES (?,?)";
		jdbcTemplate.update(query,viewerId,dataId);
	}

	@Override
	public boolean removeData(int id,int ownerId) {
		boolean success = true;
		String query = "DELETE FROM data WHERE d_id = ? and owner_id = ?";
		try {
		jdbcTemplate.update(query,id,ownerId);
		}catch (Exception e) {
			success = false;
		}
		return success;
	}

	@Override
	public Data getData(String hash,int owner) {
		String query = "SELECT * from data WHERE hash = ? and owner_id = ?";
		Object[] args = new Object[] {hash,owner};
		int[] argsType = new int[] {Types.VARCHAR,Types.INTEGER};
		List<Data> result = jdbcTemplate.query(query, args,argsType,new DataRowMapperImpl());
		return result.size()>0?result.get(0):null;
	}
	
	@Override
	public Data getSharedData(int d_id,int viewer) {
		System.out.println(d_id+" "+viewer);
		String query = "SELECT * FROM data WHERE d_id IN (SELECT d_id from access WHERE d_id = ? and viewer_id = ?)";
		Object[] args = new Object[] {d_id,viewer};
		int[] argsType = new int[] {Types.INTEGER,Types.INTEGER};
		List<Data> result = jdbcTemplate.query(query, args,argsType,new DataRowMapperImpl());
		System.out.println(result);
		return result.size()>0?result.get(0):null;
	}
	
	@Override
	public List<Data> getAllData(int owner) {
		String query = "SELECT * from data WHERE owner_id = ?";
		Object[] args = new Object[] {owner};
		int[] argsType = new int[] {Types.INTEGER};
		List<Data> result = jdbcTemplate.query(query, args,argsType,new DataRowMapperImpl());
		return result;
	}
	
	@Override
	public List<Data> getAllSharedData(int viewer) {
		String query = "SELECT * FROM data WHERE d_id IN (SELECT d_id FROM access WHERE viewer_id = ?) and owner_id != ?";
		Object[] args = new Object[] {viewer,viewer};
		int[] argsType = new int[] {Types.INTEGER,Types.INTEGER};
		List<Data> result = jdbcTemplate.query(query, args,argsType,new DataRowMapperImpl());
		return result;
	}
	
}
