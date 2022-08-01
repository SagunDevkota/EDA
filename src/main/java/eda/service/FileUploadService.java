package eda.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import eda.dao.DataDAOImpl;
import eda.dto.Data;
import eda.exception.DuplicateFileException;

public class FileUploadService {
	CommonsMultipartFile file;
	HttpSession session;
	DataDAOImpl dataDAOImpl;
	public FileUploadService() {
	}
	public FileUploadService(CommonsMultipartFile file, HttpSession session,DataDAOImpl dataDAOImpl) {
		this.file = file;
		this.session = session;
		this.dataDAOImpl = dataDAOImpl;
	}
	
	public boolean uploadFileToDatabase() throws IOException {
		byte[] bytes = file.getBytes();
		String dataString = new String(bytes);
		String hashedData = hashData(dataString);
		if(hashedData == null) {
			return false;
		}
		String path = session.getServletContext().getRealPath("/WEB-INF/resources/csv/")+hashedData+".csv";
		Data initData = initializeObject(path,hashedData);
		
		Data response = dataDAOImpl.getData(hashedData,(int)session.getAttribute("id") );
		if(response == null) {
			FileOutputStream fos = null;
			fos = new FileOutputStream(path);
			fos.write(bytes);
			System.out.println("file uploaded at "+path);
			fos.close();
			try {
				dataDAOImpl.saveData(initData);
			}catch(Exception e) {
				throw new RuntimeException(e.getLocalizedMessage());
			}
			System.out.println("New file uploaded");
		}else {
			System.out.println("New file not uploaded");
			throw new DuplicateFileException("File already uploaded.");
		}
		return true;
	}
	private Data initializeObject(String path, String hashedData) {
		Data data = new Data();
		data.setFileName(file.getOriginalFilename());
		data.setFileSize(file.getSize());
		data.setFileUrl(path);
		data.setOwnerId((int)session.getAttribute("id"));
		data.setHash(hashedData);
		return data;
	}
	
	private String hashData(String data) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(data.getBytes(StandardCharsets.UTF_8));
			String hex = String.format("%064x", new BigInteger(1, hash));
			return hex;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
