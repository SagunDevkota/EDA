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
				columnMetadata.put("histData", numericalColumnInsights.getHistogramData());
			}else if(dataType[i].equals(Constants.CATEGORICAL)) {
				
				CategoricalColumnInsights categoricalColumnInsights = new CategoricalColumnInsights(data.get(i));
				HashMap<String, Integer> list = categoricalColumnInsights.getFrequencyData();
//		        list.sort(Entry.comparingByValue());
//		        ArrayList<Map.Entry<String,Integer>> frequencyList = new ArrayList<>();
//		        int size = list.size()-1;
//		        for(int iterator = size;iterator>=size-5;iterator--) {
//		        	frequencyList.add(list.get(iterator));
//		        	if(iterator == 0) {
//		        		break;
//		        	}
//		        }
				columnMetadata.put("columnType", Constants.CATEGORICAL);
				columnMetadata.put("columnName", headers.get(i));
				columnMetadata.put("columnMetadata", categoricalColumnInsights.getColumnMetadata());
				columnMetadata.put("catFrequency", list);
				
			}else {
				
				BooleanColumnInsights booleanColumnInsights = new BooleanColumnInsights(data.get(i));
				columnMetadata.put("columnType", Constants.BOOLEAN);
				columnMetadata.put("columnName", headers.get(i));
				columnMetadata.put("columnMetadata", booleanColumnInsights.getColumnMetadata());
				columnMetadata.put("boolFrequency", booleanColumnInsights.getFrequencyData());
				columnMetadata.put("histData", booleanColumnInsights.getHistogramData());
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


	@SuppressWarnings("unchecked")
	public HashMap<String, HashMap<String, Double>> getCorrelationData(ArrayList<List<String>> data,
			List<String> headers, String[] dataType,List<HashMap<String, Object>> columnMetadata) {
		HashMap<String, HashMap<String, Double>> correlationTable = new HashMap<>();
		ArrayList<Integer> numericData = getOnlyNumericColumn(data,dataType);
		System.out.println(numericData.size()+" "+data.get(numericData.get(0)).size());
		for(int i = 0;i<numericData.size();i++) {
			double meanX = ((HashMap<String, Double>) columnMetadata.get(numericData.get(i)).get("columnMetadata")).get("Mean");
//			System.out.println(meanX+" "+headers.get(numericData.get(i)));
			HashMap<String, Double> map = new HashMap<>();
			for(int j = 0;j<numericData.size();j++) {
				int countObservation = 0;
				double partialSum = 0;
				double meanY = 0;
				for(int k = 0;k<data.get(numericData.get(i)).size();k++) {
					meanY = ((HashMap<String, Double>) columnMetadata.get(numericData.get(j)).get("columnMetadata")).get("Mean");
					if(!(data.get(numericData.get(i)).get(k).equals("") || data.get(numericData.get(j)).get(k).equals(""))) {
//						System.out.println(data.get(numericData.get(i)).get(k)+" "+data.get(numericData.get(j)).get(k));
						double XXbar = Double.parseDouble(data.get(numericData.get(i)).get(k))-meanX;
						double YYbar = Double.parseDouble(data.get(numericData.get(j)).get(k))-meanY;
						countObservation++;
						partialSum+=(XXbar*YYbar);
					}
				}
				double varX = ((HashMap<String, Double>) columnMetadata.get(numericData.get(i)).get("numStats")).get("Standard Deviation");
				double varY = ((HashMap<String, Double>) columnMetadata.get(numericData.get(j)).get("numStats")).get("Standard Deviation");
				map.put(headers.get(numericData.get(j)), (partialSum/countObservation)/(varX*varY));
//				System.out.println((partialSum/countObservation)/(varX*varY)+" "+headers.get(numericData.get(i))+" "+headers.get(numericData.get(j))+" "+meanX+" "+meanY);
			}
			correlationTable.put(headers.get(numericData.get(i)), map);
		}
//		System.out.println(numericData);
		return correlationTable;
	}


	private ArrayList<Integer> getOnlyNumericColumn(ArrayList<List<String>> data, String[] dataType) {
		ArrayList<Integer> numericData = new ArrayList<>();
		for(int i = 0;i<dataType.length;i++) {
			if(dataType[i].equals(Constants.NUMERIC)) {
				numericData.add(i);
			}
		}
		return numericData;
	}
	
}
