/*********************************
 * Assignment #1
 * FileName: PartOne.java
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
import java.util.List;

public class PartOne {

	/**
	 * @param args
	 */
	private HashMap<String, ProtocolService> map;
	private List<String> topTen;
	
	public PartOne(){
		this.map = new HashMap<String, ProtocolService>();
		this.topTen= new ArrayList<String>();
	}

	public void readServiceProtocolPairsIntoFile(String filename) {
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
				String key = null;
				
				while ((lineContent = inputStream.readLine()) != null) {
					String[] resultingTokens = lineContent.split(",");
					
					// 2nd field id protocol name and 3rd field is service name
					// used toLowerCase() to convert everything into lower case.
					// Avoid confusion between HTTP and http if present
					ProtocolService pair = new ProtocolService(
							resultingTokens[1].trim().toLowerCase(),
							resultingTokens[2].trim().toLowerCase()
							);

					// Key for HashMap
					key = pair.getKey();

					// Store count in HashMap
					if (this.map.containsKey(key)) {
						this.map.get(key).IncrementCounter();
					} else {
						pair.IncrementCounter();
						this.map.put(key, pair);
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
	
	public List<String> getTopTenPSPairs(){
		if(this.topTen.size()==0){
			
			List<ProtocolService> list = new ArrayList<ProtocolService>(map.values());
	
			Collections.sort(list, new Comparator<ProtocolService>() {
				@Override
				public int compare(ProtocolService o1, ProtocolService o2) {
					return o2.getCounter() - o1.getCounter();
				}
			});

			for (int i = 0; i < 10; i++) {
				this.topTen.add( list.get(i).getKey() + " : "+ list.get(i).getCounter() );
			}
		}
		return this.topTen;
	}

	public static void main(String[] args) {
		// HashMap to store count of (protocol,service) pair
		PartOne p1=new PartOne();
		p1.readServiceProtocolPairsIntoFile("packets.txt");
		List<String> temp=p1.getTopTenPSPairs();
		
		System.out.println("Top 10 occuring protocol service pairs: ");
		for(int i=0;i<10;i++){
			System.out.println((i+1)+" "+temp.get(i));
		}
	}
}
