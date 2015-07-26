/*********************************
 * Assignment #1
 * FileName: UserHashMap.java
 *********************************
 * Team Members:
 * Richa Kandlikar
 * Sai Phaninder Reddy Jonnala
 * *******************************
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class UserHashMap {
	private HashMap<String, User> users;
	private Integer count;
	private Integer duplication;
	private List<User> userValues;
	
	public UserHashMap(){
		this.users = new HashMap<String,User>();
		this.count=0;
		this.duplication=0;
	}
	
	public void addUser(User user){
		if(users.containsKey(user.getHashKey())){
			System.out.println("Warning: Duplication in input file: "+user.getHashKey());
			this.duplication++;
		}
		else{
			users.put(user.getHashKey(), user);
			this.count++;
		}
	}
	
	public boolean userExists(String userHashKey){
		if(users.containsKey(userHashKey)) return true;
		else return false;
	}

	/**
	 * @return the count
	 */
	public Integer getCount() {
		return count;
	}

	/**
	 * @return the duplication
	 */
	public Integer getDuplication() {
		return duplication;
	}
	
	/**
	 * prints the items in the hashmap in ascending order with respect to the user age.
	 */
	public void printItemsInAscendingByAge(){
		userValues = new ArrayList<User>(users.values());
		
		Collections.sort(userValues, new Comparator<User>() {
	        public int compare(User o1, User o2) {
	            return o1.getAge() - o2.getAge();
	        }
	    });
		
		for(User u : userValues){
			System.out.println(u.getHashKey());
		}
	}
}
