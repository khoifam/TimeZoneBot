package javabot2;

import java.io.IOException;
import java.util.ArrayList;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class JDALoader extends ListenerAdapter {
	private static JDA jda;
	private TopicAuto3 ram;
	private Memory memory;
	private ArrayList<TextChannel> textchannels;
	
	JDALoader() throws Exception
	{
		jda = JDABuilder.createDefault("NjI0Mzk4NzAxNzcwNzAyODU4.XYQamw.rmhezzKk5iwKUmJxnmMtLZQaa40").build().awaitReady();
		ram = new TopicAuto3();
		memory = new Memory();
		ram.initTextChannels(memory.retrieveTextChannels()); //add all text channels from the txt file to the arraylist
		textchannels = ram.getTextChannels();
	}
	
	@Override
	public void onMessageReceived(MessageReceivedEvent e)
	{
		
		if (e.getAuthor().isBot() == true) return;
		if (e.getMessage().getContentRaw().equalsIgnoreCase("addbingbong")) //add the text channel to the arraylist
		{
			if (ram.addTextChannel(e.getTextChannel())) //the add command
			{
				e.getChannel().sendMessage("Time zones are now being updated every 10 minutes for this text channel!").queue();

			}
			else
			{
				e.getChannel().sendMessage("The bot is already running for this text channel.").queue();
			}
						
			try {
				//memory.addTextChannel(e.getTextChannel());
				memory.updateTextChannel(textchannels);
			} catch (IOException event) {
				// TODO Auto-generated catch block
				event.printStackTrace();
			}
		}
		
		if (e.getMessage().getContentRaw().equalsIgnoreCase("bingbong")) //show the text channel's state
		{
			if (ram.getStatus(e.getTextChannel()))  //the get status command
			{
				e.getChannel().sendMessage("The bot is already running for this text channel.").queue();

			}
			else
			{
				e.getChannel().sendMessage("The bot is not activated for this text channel. Type addbingbong to activate.").queue();
			}
		}
		
		if (e.getMessage().getContentRaw().equalsIgnoreCase("removebingbong"))
		{
			if (ram.removeTextChannel(e.getTextChannel())) //the remove command
			{
				e.getChannel().sendMessage("This bot is now deactivated for this text channel.").queue();
				
				try 
				{	
					//memory.removeTextChannel(e.getTextChannel());
					memory.updateTextChannel(textchannels);
				} 
				catch (IOException event) 
				{
					// TODO Auto-generated catch block
					event.printStackTrace();
				}
			}
			else
			{
				e.getChannel().sendMessage("The bot is already deactivated for this text channel. Type addbingbong to activate.").queue();
			}
		}
	}
	
	public static JDA getJDA()
	{
		return jda;
	}
	
}
