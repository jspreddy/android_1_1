/*********************************
 * Assignment #1
 * FileName: PartTwo.java
 *********************************
 * Team Members:
 * Richa Kandlikar
 * Sai Phaninder Reddy Jonnala
 * *******************************
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class PartTwo {

	/**
	 * @param args
	 */
	private UserHashMap oldUsers;
	private UserHashMap newRecords;
	private String OldFile="userList1.txt";
	private String NewFile="userList1New.txt";
	
	
	public PartTwo(){
		this.oldUsers=new UserHashMap();
		this.newRecords=new UserHashMap();
	}

	private void readFileIntoOldUsers(String filename) {
		// Lets make sure the file path is not empty or null
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
		}
		String filePath = System.getProperty("user.dir") + "/src/" + filename;
		
		BufferedReader inputStream = null;
		// We need a try catch block so we can handle any potential IO errors
		try {
			try {
				inputStream = new BufferedReader(new FileReader(filePath));
				String lineContent = null;
				
				while ((lineContent = inputStream.readLine()) != null) {
					this.oldUsers.addUser(new User(lineContent));
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
	
	private void checkAndGetDifferencesFromNewFileIntoNewRecords(String filename) {
		if (filename == null || filename.isEmpty()) {
			System.out.println("Invalid File Path");
		}
		String filePath = System.getProperty("user.dir") + "/src/" + filename;
		BufferedReader inputStream = null;
		// We need a try catch block so we can handle any potential IO errors
		try {
			try {
				inputStream = new BufferedReader(new FileReader(filePath));
				String lineContent = null;
				
				while ((lineContent = inputStream.readLine()) != null) {
					User tmp=new User(lineContent);
					//if tmp user doesn't exist in oldUsers list, them add to newRecords
					if( !this.oldUsers.userExists(tmp.getHashKey()) ){
						this.newRecords.addUser(tmp);
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
	
	public int getNewUserCount(){
		return this.newRecords.getCount();
	}

	public static void main(String[] args) {
		System.out.println("");
		PartTwo p2=new PartTwo();
		
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("--Comparing "+p2.OldFile+" with "+p2.NewFile+"--");
		System.out.println("--------------------------------------------------------------------------------");
		
		p2.readFileIntoOldUsers(p2.OldFile);
		p2.checkAndGetDifferencesFromNewFileIntoNewRecords(p2.NewFile);
		
		int dup1=p2.oldUsers.getDuplication();
		if(dup1 >0){
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Duplication in Input File: "+p2.OldFile+", Count:"+dup1);
			System.out.println("--------------------------------------------------------------------------------");
		}
		
		int dup2=p2.newRecords.getDuplication();
		if(dup2 >0){
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println("Duplication in Input File: "+p2.NewFile+", Count:"+dup2);
			System.out.println("--------------------------------------------------------------------------------");
		}
		
		int count=p2.getNewUserCount();
		System.out.println("");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("---------------------- "+count+" new Records in "+p2.NewFile+" ----------------------");
		System.out.println("--------------------------------------------------------------------------------");
		p2.newRecords.printItemsInAscendingByAge();
	}
}
