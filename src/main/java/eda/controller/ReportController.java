package eda.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;

import eda.dao.DataDAOImpl;
import eda.dto.Data;
import eda.report.report.ReportInitiator;

@Controller
public class ReportController {
	@Autowired
	DataDAOImpl dataDAOImpl;
	
	@Autowired
	ReportInitiator reportInitiator;
	
	@Autowired
	Gson gson;
	
	@RequestMapping("/reports")
	public String getReports(HttpSession session,Model model) {
		int id = (int) session.getAttribute("id");
		System.out.println(id);
		List<Data> allReports = dataDAOImpl.getAllData(id);
		model.addAttribute("reports", allReports);
		return "reports";
	}
	
	@RequestMapping(value = "/report" ,params = {"id"})
	public String showReport(@RequestParam("id") int fileId,HttpSession session,Model model) {
		HashMap<String, Object> report = new HashMap<>();
		System.out.println(fileId);
		try {
			Data shared = dataDAOImpl.getSharedData(fileId, (int)session.getAttribute("id"));
			System.out.println(shared);
			if((shared) != null) {
				report = reportInitiator.getReport(session.getServletContext().getRealPath("/WEB-INF/resources/csv/")+shared.getHash()+".csv");
			}else {
				model.addAttribute("error", "You are not authorized");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		model.addAttribute("json", gson.toJson(report));
		return "report";
	}
}
