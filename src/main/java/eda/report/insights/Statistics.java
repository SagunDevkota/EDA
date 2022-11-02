package eda.report.insights;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * 
 * Gives Statistical Analysis of data
 *
 */
public class Statistics {
	ArrayList<Double> row;
	double mean,notNullItems;
	/**
	 * @see NumericalColumnInsights
	 * @param row Column of dataset(Row of transposed dataset)
	 * @param mean mean of row
	 * @param notNullItems number of not null items
	 */
	public Statistics(ArrayList<Double> row, double mean, double notNullItems) {
		this.row = row;
		this.mean = mean;
		this.notNullItems = notNullItems;
	}

	/**
	 * 
	 * @return Standard deviation of given list
	 * 
	 * <br>Formula : sqrt((x-myu)^2 / n)
	 * 
	 */
	public double getStandardDeviation() {
		double partialSum = 0;
		for(int i = 0;i<row.size();i++) {
			if(row.get(i) == null) {
				continue;
			}
			partialSum+=Math.pow((row.get(i)-mean),2);
		}
		return Math.sqrt(partialSum/notNullItems);
	}
	
	/**
	 * 
	 * @return Coefficient Of Variation of given list
	 * <br>Formula : StandardDeviation/Mean.
	 */
	public double getCoefficientOfVariation() {
		return (getStandardDeviation()/mean);
	}
	
	
	/**
	 * 
	 * @return Variance of given list
	 * <br>Formula : StandardDeviation^2.
	 */
	public double getVariance() {
		return Math.pow(getStandardDeviation(), 2);
	}
	
	/**
	 * 
	 * @return Median of given list
	 * <br>Formula : <br>
	 * if n is even : [(n/2)th term + {(n/2)+1}th term]/2 <br>
	 * else : {(n+1)/2}th term 
	 */
	public double getMedian() {
		List<Double> sortedRow = new ArrayList<>();
		for(int i = 0;i<row.size();i++) {
			if(row.get(i) != null) {
				sortedRow.add(row.get(i));
			}
		}
		Collections.sort(sortedRow);
		int n = sortedRow.size();
		double median;
		if(n%2 == 0) {
			median = (sortedRow.get(n/2-1)+sortedRow.get((n/2)))/2;
		}else {
			median = sortedRow.get(n/2);
		}
		return median;
	}
	
	/**
	 * @param mean of row
	 * @return Variance of given list
	 * <br>Formula : sum((Xi – X)^3 / (N-1) * sigma^3)
	 */
	public double getSkewness(double mean) {
		double partialSum = 0;
		for(int i = 0;i<row.size();i++) {
			if(row.get(i)!=null) {
				partialSum+=Math.pow((row.get(i)-mean),3);
			}
		}
		return partialSum/((notNullItems-1)*Math.pow(getStandardDeviation(), 3));
	}
	
}
