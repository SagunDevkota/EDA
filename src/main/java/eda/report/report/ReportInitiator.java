package eda.report.report;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import eda.report.preprocessing.ReadCSV;

public class ReportInitiator {
	
	/**
	 * 
	 * @param fileName Name of file to be read
	 * @return	Returns entire report of DataSet
	 * @throws IOException
	 */	
	public HashMap<String, Object> getReport(String fileName) throws IOException{
		ReadCSV readCSV = new ReadCSV(fileName);
		ArrayList<List<String>> data = readCSV.getCSVData();
		List<String> headers = readCSV.getHeaders();
		String[] dataType = readCSV.getDataType(data);
		
		List<String> al = Arrays.asList(dataType);
		Map<String, Long> dataTypeCount = al.stream().collect(
			    Collectors.groupingBy(Function.identity(), 
			    	    Collectors.counting())); 
		
		ReportEvaluator reportEvaluator = new ReportEvaluator();
		
		HashMap<String, Object> entireReport = new HashMap<>();
		HashMap<String, Object> dataSetMetadata = reportEvaluator.getDataSetMetadata(readCSV);
		List<HashMap<String, Object>> columnMetadata = reportEvaluator.getColumnMetadata(data,headers,dataType);
		HashMap<String,HashMap<String,Double>> correlationData = reportEvaluator.getCorrelationData(data,headers,dataType,columnMetadata);
		
		entireReport.put("dataSetReport", dataSetMetadata);
		entireReport.put("columnReport", columnMetadata);
		entireReport.put("correlationTable", correlationData);
		entireReport.put("dataTypeCount", dataTypeCount);
		return entireReport;
	}
}
