package javabot2;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import net.dv8tion.jda.api.entities.TextChannel;

public class Memory {
	
	private File inputFile;
	
	Memory() 
	{
		inputFile = new File("C:\\Users\\phamk\\Desktop\\discord\\javabot2\\memory.txt");
	}
	
	public void addTextChannel(TextChannel textchannel) throws IOException  //add one text channel to the txt file
	{
		Scanner in = new Scanner(inputFile);
		PrintWriter out = new PrintWriter(new FileWriter(inputFile, true)); //FileWriter prevents overriding
		
		while (in.hasNextLong())
		{
			if (in.nextLong() == textchannel.getIdLong())
			{
				//textchannel.sendMessage("This text channel is already added to the memory").queue();
				in.close();
				out.close();
				return;
			}
		}
		System.out.println("SUP");
		out.println(textchannel.getIdLong());
		//textchannel.sendMessage("This text channel is now added to the memory.").queue();
		System.out.println("SUP2");
		in.close();
		out.close();
	}
	
	public void updateTextChannel(ArrayList<TextChannel> textchannels) throws IOException
	{
		PrintWriter out = new PrintWriter(inputFile);
		for (int i = 0; i < textchannels.size(); i++)
		{
			out.println(textchannels.get(i).getIdLong());
		}
		out.close();
	}
	
	public ArrayList<TextChannel> retrieveTextChannels() throws IOException  //return all text channels from the txt file
	{
		Scanner in = new Scanner(inputFile);
		ArrayList<TextChannel> output = new ArrayList<TextChannel>();
		while (in.hasNextLong())
		{
			output.add(JDALoader.getJDA().getTextChannelById(in.nextLong()));
		}
		in.close();
		return output;	
	}
	
	public void removeTextChannel(TextChannel textchannel) throws IOException
	{
		
	}
	
}