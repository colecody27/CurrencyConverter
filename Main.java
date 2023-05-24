package currencyconverter;

import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javax.swing.*;
import java.awt.*;


class currencyConverter{
	static DecimalFormat df = new DecimalFormat("0.0000");
	public static void main(String[] args) {

		//Create hashmaps to store the country rates and currency names
		HashMap<String, Float> rates = new HashMap<String, Float>();
		
		//Read in exchange rates from CSV
		Scanner sc = null;
		try {
			sc = new Scanner(new File("ExchangeRates.csv"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		//Push each country and exchange rate into hashmap
		boolean headerLine = true;
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			if(headerLine) {
				headerLine = false;
				continue;
			}
			
			String[] tokens = line.split(",");
			rates.put(tokens[3], Float.parseFloat(tokens[4]));
		}
		sc.close();
		
		//Add United States currency 
		rates.put("United States - Dollar" , (float)1);
		
		//GUI
		MyFrame frame = new MyFrame(rates);
	}
}