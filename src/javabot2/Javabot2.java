package javabot2;

import net.dv8tion.jda.api.JDA;

public class Javabot2 {
	
	public static void main(String[] args) throws Exception {
		JDALoader jdaLoader = new JDALoader();
		JDA api = JDALoader.getJDA();
		
		api.addEventListener(jdaLoader);
		api.addEventListener(new MyListener());
		
	}
	/**
	 * To-do:
	 * 
	 * What if the text file of text channels is gone? 
	 * What if the token text file is gone?
	 * What if the token is invalid?
	 * Add console warning if a file is not found
	 * 
	 * Needs to improve naming
	 * Move the comments to the top and keep under 70
	 * 
	 * Change the arraylist into a hash table (HashSet)
	 * Serialization
	 * 
	 * Gradle & Maven
	 */
	
}
