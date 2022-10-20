package eda.dto;

import java.sql.Timestamp;

public class Data {
	int id;
	String fileName;
	String fileUrl;
	int ownerId;
	long fileSize;
	Timestamp createdTime;
	Timestamp accessedTime;
	String hash;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public int getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	public Timestamp getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Timestamp createdTime) {
		this.createdTime = createdTime;
	}
	public String getHash() {
		return hash;
	}
	public void setHash(String hash) {
		this.hash = hash;
	}
	public Timestamp getAccessedTime() {
		return accessedTime;
	}
	public void setAccessedTime(Timestamp accessedTime) {
		this.accessedTime = accessedTime;
	}
	@Override
	public String toString() {
		return "Data [id=" + id + ", fileName=" + fileName + ", fileUrl=" + fileUrl + ", ownerId=" + ownerId
				+ ", fileSize=" + fileSize + ", createdTime=" + createdTime + ", accessedTime=" + accessedTime
				+ ", hash=" + hash + "]";
	}
	
	
}
