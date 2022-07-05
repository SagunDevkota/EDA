package eda.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.google.gson.Gson;

import eda.dao.DataDAOImpl;
import eda.dto.UploadedFile;
import eda.report.report.ReportInitiator;
import eda.dto.User;

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
	@ResponseBody
	@RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
	public String uploadCSV(@RequestParam("file") CommonsMultipartFile file,HttpSession session) {
		System.out.println(file.getName());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getStorageDescription());
		byte[] bytes = file.getBytes();
		String path = session.getServletContext().getRealPath("/WEB-INF/resources/csv/")+file.getOriginalFilename();
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
		return "done";
	}
	
	@RequestMapping("/report")
	public String showReport(@RequestParam("name") String fileName,HttpSession session,Model model) {
		System.out.println(fileName);
		HashMap<String, Object> report = new HashMap<>();
		System.out.println(dataDAOImpl.contains(12345)+" "+session.getAttribute("id"));
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
