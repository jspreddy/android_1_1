/*********************************
 * Assignment #1
 * FileName: PartThree.java
 *********************************
 * Team Members:
 * Richa Kandlikar
 * Sai Phaninder Reddy Jonnala
 * *******************************
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class PartThree {

	private HashMap<String, List<String>> map;
	private String filename = "countries-info.txt";
	
	public PartThree(){
		this.map = new HashMap<String, List<String>>();
	}
	
	public void readFileAtPath(String filename) {
		// Lets make sure the file path is not empty or null
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
			return;
		}
		String filePath = System.getProperty("user.dir") + "/src/" + filename;
		BufferedReader inputStream = null;
		// We need a try catch block so we can handle any potential IO errors
		try {
			try {
				inputStream = new BufferedReader(new FileReader(filePath));
				String lineContent = null;

				// Loop will iterate over each line within the file.
				// It will stop when no new lines are found.
				while ((lineContent = inputStream.readLine()) != null) {
					Country c = new Country(lineContent);
					String key = c.getContinent();
					
					// Add continents(key) into HashMap and contries to corresponding Lists
					if (this.map.containsKey(key)) {
						this.map.get(key).add(c.getCountryName());
					} else {
						List<String> list = new ArrayList<String>();
						list.add(c.getCountryName());
						this.map.put(key, list);
					}
				}
			}
			// Make sure we close the buffered reader.
			finally {
				if (inputStream != null)
					inputStream.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void printMaps(){
		// Sort the country list
		System.out.println("-------------------------------------------------------------");
		Iterator<String> itr = this.map.keySet().iterator();
		while (itr.hasNext()) {
			String continentName = (String) itr.next();
			List<String> l = new ArrayList<String>();
			l = (List) this.map.get(continentName);
			Collections.sort(l, new Comparator<String>() {
				public int compare(String s1, String s2) {
					if (s1.compareTo(s2) > 0) {
						return 1;
					} else if (s1.compareTo(s2) < 0) {
						return -1;
					} else {
						return 0;
					}
				}
			});
			
			System.out.print(continentName+":\n\t");
			for (String countryName : l)
				System.out.print(countryName + ", ");
			System.out.println();
			System.out.println("-------------------------------------------------------------");
		}
	}
	
	public static void main(String[] args) {
		
		PartThree p3=new PartThree();
		p3.readFileAtPath(p3.filename);
		p3.printMaps();
	}

}
