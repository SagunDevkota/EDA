package eda.dao;

import java.sql.Timestamp;
import java.util.List;

import eda.dto.Data;

public interface DataDAO {
	public Data getData(String hash,int owner);
	public void saveData(Data data);
	public void shareData(int viewerId,int dataId);
	public boolean removeData(int id,int ownerID);
	public List<Data> getAllData(int owner);
	public Data getSharedData(int d_id,int viewer);
	public List<Data> getAllSharedData(int viewer);
	public void updateAccess(int fileId);
	public Timestamp getAccessTime(int data,int viewer);
}
