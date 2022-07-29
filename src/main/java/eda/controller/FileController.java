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
import eda.exception.DuplicateFileException;
import eda.report.report.ReportInitiator;
import eda.service.FileUploadService;

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
	public String uploadCSV(@RequestParam("file") CommonsMultipartFile file,HttpSession session,Model model) {
		if(!file.getContentType().equals("text/csv")) {
			return "redirect:upload?error=Invalid File Format";
		}
		FileUploadService fileUploadService = new FileUploadService(file,session,dataDAOImpl);
		boolean response = false;
		try {
			response = fileUploadService.uploadFileToDatabase();
		} catch (IOException e) {
			model.addAttribute("Error", e.getLocalizedMessage());
		} catch(DuplicateFileException e) {
			model.addAttribute("Error", e.getLocalizedMessage());
		}
		model.addAttribute("response", response);
		return "UploadResponse";
	}
	
	@RequestMapping("/report")
	public String showReport(@RequestParam("name") String fileName,HttpSession session,Model model) {
		System.out.println(fileName);
		HashMap<String, Object> report = new HashMap<>();
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
