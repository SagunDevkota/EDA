package eda.report.insights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import eda.report.constants.*;
import eda.report.preprocessing.ReadCSV;

/**
 * 
 * Provides insights for boolean type of column.
 * <br>Note : true/false and 1/0 are identified as boolean columns.
 */
public class BooleanColumnInsights{
	ArrayList<Boolean> row;
	double missing,distinct;
	
	/**
	 * 
	 * @param row Column of dataset(Row of transposed dataSet)
	 * @see ReadCSV
	 * Converts values of column from String(Read as String from file) to boolean.
	 */
	public BooleanColumnInsights(List<String> row) {
		this.row = new ArrayList<Boolean>();
		for(String col:row) {
			if(col == "") {
				this.row.add(null);
			}else {
				if(col.equals("1")||col.equals(Constants.TRUE_LITERAL)) {
					this.row.add(true);
				}else if(col.equals("0") || col.equals(Constants.FALSE_LITERAL)){
					this.row.add(false);
				}
			}
		}
		dataAnalyzer();
	}
	
	/**
	 * @return get distinct count elements.
	 * Used by getColumnMedata()
	 */
	private double getDistinct() {
		return distinct;
	}

	/**
	 * @return get missing data count.
	 * Used by getColumnMedata()
	 */
	private double getMissing() {
		return missing;
	}

	/**
	 * 
	 * @return Returns HashMap with count of missing and distinct.
	 */
	public HashMap<String, Double> getColumnMetadata() {
		HashMap<String,Double> map = new HashMap<>();
		map.put("Missing", getMissing());
		map.put("Distinct", getDistinct());
		return map;
	}

	/**
	 * 
	 * @return Returns HashMap with count of true and false.
	 */
	public HashMap<String, Integer> getFrequencyData(){
		HashMap<String,Integer> frequency = new HashMap<>();
		for(int i = 0;i<row.size();i++) {
			if(row.get(i) != null) {
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
	 * Calculate distinct,missing.
	 */
	private void dataAnalyzer() {
		HashSet<Boolean> distinctSet = new HashSet<>();
		for(int i = 0;i<row.size();i++) {
			if(row.get(i) == null) {
				missing++;
				continue;
			}
			boolean val = row.get(i);
			distinctSet.add(val);
		}
		distinct = (double)(distinctSet.size());
	}
	
	/**
	 * 
	 * @return returns range and frequency of occurrence of data for histogram
	 */
	public HashMap<String,Integer> getHistogramData(){
		HashMap<String, Integer> histData = new HashMap<>();
		ArrayList<Boolean> nullRemovedRow = new ArrayList<>(row);
		nullRemovedRow.removeIf(Objects::isNull);
		boolean[] list = new boolean[] {true,false};
		for(int i = 0;i<2;i++) {
			final int c = i;
			List<Boolean> collect = nullRemovedRow.stream().collect(Collectors.groupingBy(v->(v== list[c]))).get(true);
			if(collect != null) {
				if(i == 0) {
					histData.put("1.0", collect.size());
				}else {
					histData.put("0.0", collect.size());
				}
				
			}else {
				if(i == 0) {
					histData.put("1.0", 0);
				}else {
					histData.put("0.0", 0);
				}
			}
		}
		return histData;
	}
}
