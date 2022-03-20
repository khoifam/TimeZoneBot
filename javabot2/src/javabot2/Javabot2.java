package javabot2;

import net.dv8tion.jda.api.JDA;

public class Javabot2 {
	
	public static void main(String[] args) throws Exception {
		JDALoader jdaLoader = new JDALoader();
		JDA api = JDALoader.getJDA();
		
		api.addEventListener(jdaLoader);
		api.addEventListener(new MyListener());
		
	}
	
	
}
