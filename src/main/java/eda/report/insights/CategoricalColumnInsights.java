package eda.report.insights;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 
 * @author Sagun Devkota
 * Provides insights for categorical type of column.
 */
public class CategoricalColumnInsights {
	private double missing,distinct,minLength,maxLength,totalLength,itemCount;
	List<String> row;
	
	/**
	 * 
	 * @param row Column of dataset(Row of transposed dataSet)
	 * Note: for transposed logic visit ReadCSV from preprocessing package.
	 * initiates dataAnalyzer()
	 */
	public CategoricalColumnInsights(List<String> row) {
		this.row = row;
		dataAnalyzer();
	}
	
	/**
	 * @return: get distinct count elements.
	 * Used by getColumnMedata()
	 */
	private double getDistinct() {
		return distinct;
	}

	
	/**
	 * @return: get missing data count.
	 * Used by getColumnMedata()
	 */
	private double getMissing() {
		// TODO Auto-generated method stub
		return missing;
	}
	
	private double getMinLength(){
		return minLength;
	}
	
	private double getMaxLength() {
		return maxLength;
	}
	
	private double getAverageLength() {
		return totalLength/itemCount;
	}
	/**
	 * 
	 * @return: Returns HashMap with frequency of each occurrence of every data.
	 */
	public HashMap<String, Integer> getFrequencyData(){
		HashMap<String,Integer> frequency = new HashMap<>();
		minLength = row.get(0).length();
		maxLength = row.get(0).length();
		for(int i = 0;i<row.size();i++) {
			int currentLength = row.get(i).length();
			if(currentLength < minLength){
				minLength = currentLength;
			}else if(currentLength > maxLength) {
				maxLength = currentLength;
			}
			if(row.get(i) != null && !row.get(i).equals("")) {
				itemCount++;
				totalLength += currentLength;
				if(frequency.get(""+row.get(i)) == null) {
					frequency.put(""+row.get(i), 1);
				}else {
					Integer integer = frequency.get(""+row.get(i));
					frequency.put(""+row.get(i), ++integer);
				}
			}
		}
		return frequency;
	}
	
	/**
	 * @return: Returns HashMap with count of missing and distinct.
	 */
	public HashMap<String, Double> getColumnMetadata() {
		HashMap<String,Double> map = new HashMap<>();
		map.put("Missing", getMissing());
		map.put("Distinct", getDistinct());
		map.put("MinimumLength", getMinLength());
		map.put("MaximumLength", getMaxLength());
		map.put("AverageLength", getAverageLength());
		return map;
	}
	
	/**
	 * Calculates distinct,missing.
	 */
	private void dataAnalyzer() {
		HashSet<String> distinctSet = new HashSet<>();
		for(int i = 0;i<row.size();i++) {
			if(row.get(i).equals("")) {
				missing++;
				continue;
			}
			String val = row.get(i);
			distinctSet.add(val);
		}
		distinct = (double)(distinctSet.size());
	}
}
