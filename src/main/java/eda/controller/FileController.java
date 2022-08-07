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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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
	
	@RequestMapping("/upload")
	public String upload(Model model) {
		model.addAttribute("uploadedFile", new UploadedFile());
		return "upload";
	}

	@RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
	public RedirectView uploadCSV(@RequestParam("file") CommonsMultipartFile file,HttpSession session,RedirectAttributes redir) {
		RedirectView redirectView = new RedirectView("/dashboard",true);
		if(!file.getContentType().equals("text/csv")) {
			redir.addFlashAttribute("error", "Invalid File Format");
			redir.addFlashAttribute("success", false);
			return redirectView;
		}
		FileUploadService fileUploadService = new FileUploadService(file,session,dataDAOImpl);
		boolean response = false;
		try {
			response = fileUploadService.uploadFileToDatabase();
		} catch (IOException e) {
			redir.addFlashAttribute("success", response);
			redir.addFlashAttribute("error", e.getLocalizedMessage());
		} catch(DuplicateFileException e) {
			redir.addFlashAttribute("success", response);
			redir.addFlashAttribute("error", e.getLocalizedMessage());
		}
		redir.addFlashAttribute("success", response);
		redir.addFlashAttribute("message", "File Uploaded Successfully");
		return redirectView;
	}
}
