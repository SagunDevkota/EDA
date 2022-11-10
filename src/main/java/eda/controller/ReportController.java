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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.google.gson.Gson;

import eda.dao.DataDAOImpl;
import eda.dto.Data;
import eda.report.report.ReportInitiator;
import eda.service.ShareReportService;

@Controller
public class ReportController {
	@Autowired
	DataDAOImpl dataDAOImpl;
	
	@Autowired
	ReportInitiator reportInitiator;
	
	@Autowired
	Gson gson;
	
	@Autowired
	ShareReportService shareReportService;
	
	@RequestMapping("/reports")
	public String getReports(HttpSession session,Model model) {
		int id = (int) session.getAttribute("id");
		System.out.println(id);
		List<Data> allReportsOwned = dataDAOImpl.getAllData(id);
		for(Data report:allReportsOwned) {
			report.setAccessedTime(shareReportService.getAccessTime(id,report.getId()));
		}
		model.addAttribute("reportOwned", allReportsOwned);
		List<Data> allReportsShared = dataDAOImpl.getAllSharedData(id);
		model.addAttribute("reportShared", allReportsShared);
		return "reports";
	}
	
	@RequestMapping(value = "/popup",params = {"id"})
	public String getPopup(@RequestParam("id") int reportId,Model model) {
		model.addAttribute("id", reportId);
		return "popup";
	}
	
	@RequestMapping(value = "/processShare")
	public RedirectView processShare(@RequestParam("id") int reportId,@RequestParam("email") String email,HttpSession session,RedirectAttributes redir) {
		RedirectView redirectView = new RedirectView("/dashboard", true);
		shareReportService.initialize(reportId, email, (int)session.getAttribute("id"));
		boolean shareReport = shareReportService.shareReport();
		if(shareReport) {
			redir.addFlashAttribute("message", "Report Shared");
		}else {
			redir.addFlashAttribute("error", "Cannot Share Report");
		}
		redir.addFlashAttribute("success", shareReport);
		return redirectView;
	}
	
	@RequestMapping(value="/processDelete", params = {"id"})
	public RedirectView processDelete(@RequestParam("id") int id,HttpSession session,RedirectAttributes redir) {
		RedirectView redirectView = new RedirectView("/dashboard",true);
		boolean removeData = dataDAOImpl.removeData(id,(int)session.getAttribute("id"));
		if(removeData) {
			redir.addFlashAttribute("message", "File Deleted Successfully");
		}else {
			redir.addFlashAttribute("error", "Could not delete file");
		}
		redir.addFlashAttribute("success", removeData);
		return redirectView;
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
				shareReportService.updateAccess(fileId);
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
