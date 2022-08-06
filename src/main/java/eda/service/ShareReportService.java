package eda.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eda.dao.DataDAOImpl;
import eda.dao.UserDAOImpl;
import eda.dto.Data;
import eda.dto.User;

@Service
public class ShareReportService {
	int reportId;
	String email;
	int ownerId;
	@Autowired
	DataDAOImpl dataDAOImpl;
	@Autowired
	UserDAOImpl userDAOImpl;
	public void initialize(int reportId, String email,int ownerId) {
		this.reportId = reportId;
		this.email = email;
		this.ownerId  = ownerId;
	}
	private boolean isOwner() {
		Data sharedData = dataDAOImpl.getSharedData(reportId, ownerId);
		return sharedData!=null?true:false;
	}
	public boolean shareReport() {
		if(isOwner()) {
			User user = userDAOImpl.findUserByUsername(email);
			if(user!= null) {
				dataDAOImpl.shareData(user.getId(), reportId);
				return true;
			}
		}
		return false;
	}
}
