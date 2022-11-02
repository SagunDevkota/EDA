package eda.report.insights;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import eda.report.preprocessing.ReadCSV;

/**
 * 
 * Provides insights for categorical type of column.
 */
public class CategoricalColumnInsights {
	double missing,distinct;
	List<String> row;
	
	/**
	 * 
	 * @param row Column of dataset(Row of transposed dataSet)
	 * @see ReadCSV
	 * initiates dataAnalyzer()
	 */
	public CategoricalColumnInsights(List<String> row) {
		this.row = row;
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
		// TODO Auto-generated method stub
		return missing;
	}
	
	/**
	 * 
	 * @return Returns HashMap with frequency of each occurrence of every data.
	 */
	public HashMap<String, Integer> getFrequencyData(){
		HashMap<String,Integer> frequency = new HashMap<>();
		for(int i = 0;i<row.size();i++) {
			if(row.get(i) != null && !row.get(i).equals("")) {
				if(frequency.get(""+row.get(i)) == null) {
					frequency.put(""+row.get(i), 1);
				}else {
					Integer integer = frequency.get(""+row.get(i));
					frequency.put(""+row.get(i), ++integer);
				}
			}
		}
		HashMap<String, Integer> sortedMap
        = frequency.entrySet()
              .stream()
              .sorted((i1, i2)
                          -> i2.getValue().compareTo(
                              i1.getValue()))
              .collect(Collectors.toMap(
                  Map.Entry::getKey,
                  Map.Entry::getValue,
                  (e1, e2) -> e1, LinkedHashMap::new));
		return sortedMap;
	}
	
	/**
	 * @return Returns HashMap with count of missing and distinct.
	 */
	public HashMap<String, Double> getColumnMetadata() {
		HashMap<String,Double> map = new HashMap<>();
		map.put("Missing", getMissing());
		map.put("Distinct", getDistinct());
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
