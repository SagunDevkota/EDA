package eda.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import eda.dao.DataDAOImpl;
import eda.dto.Data;
import eda.dto.UploadedFile;
import eda.report.report.ReportInitiator;

@Controller
public class FileController {
	
	@Autowired
	DataDAOImpl dataDAOImpl;
	
	@Autowired
	Gson gson;
	
	@Autowired
	ReportInitiator reportInitiator;
	
	@RequestMapping("/upload")
	public String upload(Model model) {
		model.addAttribute("uploadedFile", new UploadedFile());
		return "temp-upload";
	}

	@RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
	public String uploadCSV(@RequestParam("file") CommonsMultipartFile file,HttpSession session) {
		if(!file.getContentType().equals("text/csv")) {
			return "redirect:upload?error=Invalid File Format";
		}
		byte[] bytes = file.getBytes();
		String dataString = new String(bytes);
		String path = session.getServletContext().getRealPath("/WEB-INF/resources/csv/")+dataString.hashCode()+".csv";
		
		Data data = new Data();
		data.setFileName(file.getOriginalFilename());
		data.setFileSize(file.getSize());
		data.setFileUrl(path);
		data.setHash(dataString.hashCode());
		data.setOwnerId((int)session.getAttribute("id"));
		
		Data response = dataDAOImpl.getData(dataString.hashCode(),(int)session.getAttribute("id") );
		if(response == null) {
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(path);
				fos.write(bytes);
				System.out.println("file uploaded at "+path);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					fos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dataDAOImpl.saveData(data);
			System.out.println("New file uploaded");
		}else {
			System.out.println("New file not uploaded");
		}
		return "UploadResponse";
	}
	
	@RequestMapping("/report")
	public String showReport(@RequestParam("name") String fileName,HttpSession session,Model model) {
		System.out.println(fileName);
		HashMap<String, Object> report = new HashMap<>();
		System.out.println(dataDAOImpl.getData(12345,3)+" "+session.getAttribute("id"));
		try {
			report = reportInitiator.getReport(session.getServletContext().getRealPath("/WEB-INF/resources/csv/")+fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("json", gson.toJson(report));
		return "report";
	}
}
