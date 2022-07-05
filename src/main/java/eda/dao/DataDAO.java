package eda.dao;

import eda.dto.Data;

public interface DataDAO {
	public boolean contains(long hash);
	public Data getData(int id);
	public void saveData(Data data);
	public boolean shareData(int id);
	public boolean removeData(int id);
}
