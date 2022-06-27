package eda.report.preprocessing;

import java.io.FileReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import eda.report.constants.Constants;

public class ReadCSV {
	List<String> headers;
	ArrayList<List<String>> data;
	int missing = 0;
	public ReadCSV(String path) throws IOException {
		data = new ArrayList<>();
		CSVReader br = new CSVReader(new FileReader(path));
		stringToCSVFormat(br);
		data = transposeArray(data,headers);
	}
	

	/**
	 * 
	 * @param br BufferedReader object of file read.
	 * @return List of headers in given CSV File;
	 * A,B,C,D
	 * 1,1,0.2,0.3
	 * 
	 * Here this method returns [A,B,C,D]
	 * as A,B,C,D they is first line returned by br.readLine()
	 * .split(",") methods creates array by splitting on ","
	 */
	private List<String> getHeaders(CSVReader br) {
		try {
			return (List<String>) Arrays.asList(br.readNext());
		} catch (IOException | CsvValidationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param br BufferedReader object of file read.
	 * A,B,C,D (already read by getHeaders
	 * 1,1,0.2,0.3
	 * 10,10,0.22,0.31
	 * 
	 * adds data to data arrayList by parsing all data except headers
	 * @throws CsvValidationException 
	 */
	private void stringToCSVFormat(CSVReader br){
		try {
			headers = getHeaders(br);
			String[] line;
			while((line=br.readNext())!=null) {
				data.add(Arrays.asList(line));
			}
			br.close();
		}catch (IOException | CsvValidationException e) {
			// TODO: handle exception
		}
	}
	
	/**
	 * 
	 * @param data data parsed by stringToCSVFormat
	 * @param headers headings of CSV File
	 * @return transpose of data matrix
	 * Note: transpose of data matrix is now stored in data
	 * Before:
	 * 
	 * A,B,C,D
	 * 1,1,0.2,0.3
	 * 10,10,0.22,0.31
	 * 
	 * After:
	 * 
	 * A->	[[1,10],
	 * B->	[1,10],
	 * C->	[0.2,0.22],
	 * D->	[0.3,0.31]]
	 */
	private ArrayList<List<String>> transposeArray(ArrayList<List<String>> data,List<String> headers) {
		String[][] dataInverted = new String[headers.size()][data.size()];
		for(int i = 0;i<data.size();i++) {
			for(int j = 0;j<dataInverted.length;j++) {
				if(j >= data.get(i).size()) {
					dataInverted[j][i] = "";
				}else {
					dataInverted[j][i] = data.get(i).get(j).toLowerCase();
				}
			}
		}
		ArrayList<List<String>> list = (ArrayList<List<String>>) Arrays.stream(dataInverted)
                .map(Arrays::asList)
                .collect(Collectors.toList());
		return list;
	}
	
	
	/**
	 * 
	 * @param data data parsed by stringToCSVFormat
	 * @return array of dataType of every column
	 * 
	 * DataTypes can be Numerical,Categorical,Boolean
	 * 
	 * As data set is transposed row in this method is column in data set and vice-versa
	 * 
	 * Logic:
	 * type[] is an array where the length of array is equal to column in actual data set and row in transposed(current) data set.
	 * 
	 * As every Numeric,Boolean data can also be casted to String. We first try to differentiate Numeric,Boolean with String(Categorical) type.
	 * As long as a data can be identified as Numeric/Boolean it is not marked Categorical.
	 * So, any column named categorical cannot be Numeric or Boolean.
	 * Hence, if we find a column to be categorical we skip that data value;
	 * 
	 * Boolean data can be "true/false" or "0/1"
	 * So, we need to identify if any categorical can be Boolean or if any Numeric can be boolean.
	 * We term a column to be Boolean if it contains "0/1" or "true/false" and if the column is not already Numeric/Categorical.
	 * 
	 * It should not be Numeric/Categorical because a column is Numeric/Categorical if data of previous column of same row(ed) is
	 * not "0/1" or if it is not "true/false"
	 */
	public String[] getDataType(ArrayList<List<String>> data) {
		String[] type = new String[data.size()];
		boolean numericBoolean = false;
		for(int row = 0;row<data.size();row++) {
			for(int column = 0;column<data.get(0).size();column++) {
				if(data.get(row).get(column).equals("")) {
					missing++;
					continue;
				}
				if(type[row] == Constants.CATEGORICAL) {
					continue;
				}
				try {
					Double.parseDouble(data.get(row).get(column));
					if(type[row] == Constants.BOOLEAN && !numericBoolean) {
						type[row] = Constants.CATEGORICAL;
						numericBoolean = false;
					}else {
						if((data.get(row).get(column).equals("0") || data.get(row).get(column).equals("1"))&&type[row] != Constants.NUMERIC &&type[row] != Constants.CATEGORICAL) {
							numericBoolean = true;
							type[row] = Constants.BOOLEAN;
						}else {
							type[row] = Constants.NUMERIC;
						}
					}
				}catch(NumberFormatException e) {
					String cell = data.get(row).get(column).strip().toLowerCase();
					if(cell.equals(Constants.TRUE_LITERAL) || cell.equals(Constants.FALSE_LITERAL)) {
						if(type[row] == Constants.NUMERIC) {
							type[row] = Constants.CATEGORICAL;
						}else {
							type[row] = Constants.BOOLEAN;
						}
					}else{
						type[row] = Constants.CATEGORICAL;
					}
				}catch(ArrayIndexOutOfBoundsException e) {
					
				}
			}
		}
		return type;
	}
	/**
	 * 
	 * @return headers of CSV File
	 */
	public List<String> getHeaders() {
		return headers;
	}
	
	public int getMissing() {
		return missing;
		
	}
	
	public int getRecords() {
		return data.get(0).size();
	}
	
	/**
	 * 
	 * @return transposed CSV data
	 */
	public ArrayList<List<String>> getCSVData() {
		return data;
	}
}
