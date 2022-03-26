package javabot2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.Timer;
import java.util.TimerTask;

import net.dv8tion.jda.api.entities.TextChannel;

public class TopicAuto3
{
	private ArrayList<TextChannel> textchannels = new ArrayList<TextChannel>(); //list of text channels participating
	private int on = 0; //when turn to 1, timer runs
	
	public void startTimer()
	{
		if (on == 0) //start the timer only once
		{
			start();
			System.out.println("Timer started!");
			on = 1;
		}
		System.out.println(textchannels);
	}
	
	public ArrayList<TextChannel> getTextChannels()
	{
		return textchannels;
	}
	
	public boolean addTextChannel(TextChannel textchannel) //add one text channel into the arraylist
	{
		int textchannelsSize = textchannels.size();
		for (int i = 0; i < textchannelsSize; i++) //check if channel is already added
		{
			if (textchannels.get(i).equals(textchannel))
			{
				return false;
			}
		}
		
		textchannels.add(textchannel);
		startTimer(); //kinda useless since this will get called right when the program starts in initTextChannels()
		System.out.println(textchannel.getIdLong());
		System.out.println(textchannels);
		return true;
	}
	
	public boolean removeTextChannel(TextChannel textchannel) //remove one text channel from the arraylist
	{
		int textchannelsSize = textchannels.size();
		for (int i = 0; i < textchannelsSize; i++) //check if channel is already added
		{
			if (textchannels.get(i).equals(textchannel))
			{
				textchannels.remove(i);
				System.out.println(textchannels);
				return true;
			}
		}
		return false;
	}
	
	public boolean getStatus(TextChannel textchannel)
	{
		System.out.println(textchannels);
		int textchannelsSize = textchannels.size();
		for (int i = 0; i < textchannelsSize; i++) //check if channel is already added
		{
			if (textchannels.get(i).equals(textchannel))
			{
				return true;
			}
		}
		return false;
	}
	
	public void initTextChannels(ArrayList<TextChannel> database) //add text channels to this arraylist from the arraylist in Memory class
	{
		int databaseSize = database.size();
		for (int i = 0; i < databaseSize; i++)
		{
			textchannels.add(database.get(i));
		}
		textchannels.removeIf(n -> (n == null));
		startTimer();
	}
	
	Timer timer = new Timer();
	TimerTask task = new TimerTask() {
		public void run() {                  //every 10 minutes this block runs
			
			TimeZone zone = TimeZone.getTimeZone("America/Los_Angeles");
			SimpleDateFormat time = new SimpleDateFormat("hh:mm a");
			time.setTimeZone(zone);
			Date date = new Date();
			
			String pst = time.format(date);
			
			zone = TimeZone.getTimeZone("Australia/Sydney");
			time.setTimeZone(zone);
			
			String aus = time.format(date);
			
			zone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh");
			time.setTimeZone(zone);
			
			String vn = time.format(date);
			
			zone = TimeZone.getTimeZone("America/New_York");
			time.setTimeZone(zone);
			
			String est = time.format(date);
			
			int textchannelsSize = textchannels.size();
			for (int i = 0; i < textchannelsSize; i++)
			{
				System.out.println(textchannels.get(i));
				if (textchannels.get(i) != null)
				{
					textchannels.get(i).getManager().setTopic("VN: " + vn + " ---- AUS: " + aus + " ---- West: " + pst + " ---- East: " + est).queue();
				}
				System.out.println("VN: " + vn + " ---- AUS: " + aus + " ---- West: " + pst + " ---- East: " + est);
			}
		}
	};
	
	public void start()
	{
		timer.scheduleAtFixedRate(task, 600000, 600000); //600000
	}
}
