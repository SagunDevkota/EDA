package eda.report.insights;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import eda.report.preprocessing.ReadCSV;

/**
 * 
 * Provides insights for numerical columns
 */
public class NumericalColumnInsights{
	ArrayList<Double> row;
	double min,max,mean,zeros,sum,missing,distinct;
	
	/**
	 * 
	 * @param row Column of dataset(Row of transposed dataSet)
	 * @see ReadCSV
	 * initiates dataAnalyzer()
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

	/**
	 * 
	 * @return returns the maximum value
	 */
	private double getMax() {
		return max;
	}

	/**
	 * 
	 * @return returns the minimum value
	 */
	private double getMin() {
		return min;
	}

	/**
	 * 
	 * @return returns the mean value
	 */
	private double getMean() {
		return mean;
	}

	/**
	 * 
	 * @return returns number of 0's in row
	 */
	private double getZeros() {
		return zeros;
	}

	/**
	 * 
	 * @return returns number of missing data
	 */
	private double getMissing() {
		return missing;
	}

	/**
	 * 
	 * @return returns number of distinct elements
	 */
	private double getDistinct() {
		return distinct;
	}
	
	/**
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
	 * Called internally by constructor of NumericalColumnInsights<br>
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
	
	/**
	 * 
	 * @return returns range and occurrence of data for histogram
	 */
	public HashMap<String,Integer> getHistogramData(){
		HashMap<String, Integer> histData = new HashMap<>();
		double factor = ((getMax()+(0.001*getMin()/100))-getMin())/9;
		ArrayList<Double> nullRemovedRow = new ArrayList<>(row);
		nullRemovedRow.replaceAll(t -> Objects.isNull(t) ? getMin()-1 : t);
		int count = 0;
		for(double i = getMin();count<10;i = i+factor) {
			final double lower = i;
			final double higher = i+factor;
			List<Double> collect = nullRemovedRow.stream().collect(Collectors.groupingBy(v->(v>= lower && v<higher))).get(true);
			if(collect != null) {
				histData.put(""+higher, collect.size());
			}else {
				histData.put(""+higher, 0);
			}
			count++;
		}
		return histData;
	}
}
