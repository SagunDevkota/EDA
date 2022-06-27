package eda.report.insights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class NumericalColumnInsights{
	ArrayList<Double> row;
	double min,max,mean,zeros,sum,missing,distinct;
	
	/**
	 * 
	 * @param row
	 * Row data is needed in every calculation so it is taken in constructor.
	 * here row is
	 *  A->	[[1,10],
	 * B->	[1,10],
	 * C->	[0.2,0.22],
	 * D->	[0.3,0.31]]
	 * 
	 * First row [1,10] second [1,10] third [0.2,0.22] and so on
	 */
	public NumericalColumnInsights(List<String> row) {
		this.row = new ArrayList<Double>();
		for(String col:row) {
			if(col == "") {
				this.row.add(null);
			}else {
				this.row.add(Double.parseDouble(col));
			}
		}
		dataAnalyzer();
	}

	private double getMax() {
		return max;
	}


	private double getMin() {
		return min;
	}


	private double getMean() {
		return mean;
	}


	private double getZeros() {
		return zeros;
	}


	private double getMissing() {
		return missing;
	}


	private double getDistinct() {
		return distinct;
	}
	
	/**
	 * Called by external interface
	 * @return hash map of generalised mathematical data
	 */
	public HashMap<String, Double> getColumnMetadata() {
		HashMap<String,Double> map = new HashMap<>();
		map.put("Maximum", getMax());
		map.put("Minimum", getMin());
		map.put("Mean", getMean());
		map.put("Zeros", getZeros());
		map.put("Missing", getMissing());
		map.put("Distinct", getDistinct());
		return map;
	}
	
	/**
	 * Called internally by constructor of NumericalColumnInsights
	 * Calculates min,max,mean,sum,zeros,missing,distinct
	 */
	private void dataAnalyzer() {
		sum = 0;
		zeros = 0;
		int index = 0;
		while(row.get(index) == null) {
			index++;
		}
		min = row.get(index);
		max = row.get(index);
		mean = 0;
		HashSet<Double> distinctSet = new HashSet<>();
		for(int i = 0;i<row.size();i++) {
			if(row.get(i) == null) {
				missing++;
				continue;
			}
			double val = row.get(i);
			distinctSet.add(val);
			if(val == 0) {
				zeros++;
			}
			if(min > val) {
				min = val;
			}
			if(max < val) {
				max = val;
			}
			sum+=val;
		}
		distinct = (double)(distinctSet.size());
		mean = sum/(row.size()-missing);
	}
	
	/**
	 * 
	 * @return HashMap of StatisticalData
	 */
	public HashMap<String,Double> getStatisticsData() {
		HashMap<String, Double> statsData = new HashMap<>();
		Statistics stats = new Statistics(row, mean,row.size()-missing);
		statsData.put("Standard Deviation", stats.getStandardDeviation());
		statsData.put("Coefficient of Variation", stats.getCoefficientOfVariation());
		statsData.put("Variance", stats.getVariance());
		statsData.put("Median", stats.getMedian());
		statsData.put("Skewness", stats.getSkewness(mean));
		return statsData;
	}


}
