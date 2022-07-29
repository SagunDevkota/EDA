package eda.dao;

import eda.dto.Data;

public interface DataDAO {
	public Data getData(String hash,int owner);
	public void saveData(Data data);
	public boolean shareData(int id);
	public boolean removeData(int id);
}
