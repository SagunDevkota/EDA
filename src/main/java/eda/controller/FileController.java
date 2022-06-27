package eda.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import eda.dto.UploadedFile;
import eda.report.report.ReportInitiator;

@Controller
public class FileController {
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
	
	@ResponseBody
	@RequestMapping("/report")
	public String showReport(@RequestParam("name") String fileName,HttpSession session) {
		System.out.println(fileName);
		ReportInitiator reportInitiator = new ReportInitiator();
		HashMap<String, Object> report = new HashMap<>();
		try {
			report = reportInitiator.getReport(session.getServletContext().getRealPath("/WEB-INF/resources/csv/")+fileName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return report.toString();
	}
}
