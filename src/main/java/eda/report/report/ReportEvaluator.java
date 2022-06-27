package eda.report.report;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import eda.report.constants.Constants;
import eda.report.insights.BooleanColumnInsights;
import eda.report.insights.CategoricalColumnInsights;
import eda.report.insights.NumericalColumnInsights;
import eda.report.preprocessing.ReadCSV;

/**
 * 
 * @author Sagun Devkota
 * Produces report for Report Initiator
 * @see ReportInitiator
 *
 */
public class ReportEvaluator {
	/**
	 * 
	 * @param data contents of every column
	 * @param headers title of columns
	 * @param dataType data type of columns
	 * @return Gives columns all required insights
	 */
	public List<HashMap<String, Object>> getColumnMetadata(ArrayList<List<String>> data,List<String> headers,String[] dataType) {
		
		List<HashMap<String, Object>> columnsReport = new ArrayList<>();
		
		for(int i = 0;i<dataType.length;i++) {
			
			HashMap<String, Object> columnMetadata = new HashMap<>();
			
			if(dataType[i].equals(Constants.NUMERIC)) {
				
				NumericalColumnInsights numericalColumnInsights = new NumericalColumnInsights(data.get(i));
				columnMetadata.put("columnType", Constants.NUMERIC);
				columnMetadata.put("columnName", headers.get(i));
				columnMetadata.put("columnMetadata", numericalColumnInsights.getColumnMetadata());
				columnMetadata.put("numStats", numericalColumnInsights.getStatisticsData());
				
			}else if(dataType[i].equals(Constants.CATEGORICAL)) {
				
				CategoricalColumnInsights categoricalColumnInsights = new CategoricalColumnInsights(data.get(i));
				List<Entry<String, Integer>> list = new ArrayList<>(categoricalColumnInsights.getFrequencyData().entrySet());
		        list.sort(Entry.comparingByValue());
		        ArrayList<Map.Entry<String,Integer>> frequencyList = new ArrayList<>();
		        int size = list.size()-1;
		        for(int iterator = size;iterator>=size-5;iterator--) {
		        	frequencyList.add(list.get(iterator));
		        	if(iterator == 0) {
		        		break;
		        	}
		        }
				columnMetadata.put("columnType", Constants.CATEGORICAL);
				columnMetadata.put("columnName", headers.get(i));
				columnMetadata.put("columnMetadata", categoricalColumnInsights.getColumnMetadata());
				columnMetadata.put("catFrequency", frequencyList);
				
			}else {
				
				BooleanColumnInsights booleanColumnInsights = new BooleanColumnInsights(data.get(i));
				columnMetadata.put("columnType", Constants.BOOLEAN);
				columnMetadata.put("columnName", headers.get(i));
				columnMetadata.put("columnMetadata", booleanColumnInsights.getColumnMetadata());
				columnMetadata.put("boolFrequency", booleanColumnInsights.getFrequencyData());
				
			}
			columnsReport.add(columnMetadata);
		}
		return columnsReport;
	}
	
	
	/**
	 * @see ReadCSV
	 * @param readCSV CSV data from preprocessing
	 * @return returns DataSet metadata
	 */
	public HashMap<String, Object> getDataSetMetadata(ReadCSV readCSV) {
		
		HashMap<String, Object> dataSet = new HashMap<>();
		dataSet.put("columns", readCSV.getHeaders());
		dataSet.put("records", readCSV.getRecords());
		dataSet.put("missing", readCSV.getMissing());
		return dataSet;
	}
	
}
