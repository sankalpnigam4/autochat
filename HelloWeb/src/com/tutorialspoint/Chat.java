package com.tutorialspoint;

public class Chat {
		String chat;
		String message;
		String str1="\n\nBot: Please enter your Destination\n";
		String str2="\n\nBot: Thank you , your cab is booked\n";
		public String getchat()
		{
			return chat;
		}
		
		public void setchathistory(String chat)
		{
			this.chat=chat;
		}
		
		public String getmessage()
		{
			return message;
		}
	
		public void setmessage(String message)
		{
			this.message=message;
		}
		
}
