package com.tutorialspoint;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;



@Controller
public class StudentController {
	String chathistory;

   @RequestMapping(value = "/contact", method = RequestMethod.GET)
   public ModelAndView contact() {
      return new ModelAndView("contact", "command", new Contact());
   }
   
   @RequestMapping(value = "/sms", method = RequestMethod.GET)
   public ModelAndView sms() {
      return new ModelAndView("smspage", "command", new Number());
   }
   
   @RequestMapping(value="/sendsms",method=RequestMethod.POST)
   public ModelAndView sendsms(@ModelAttribute("SpringWeb")Number number, 
		   ModelMap model)
		   {
	   
	   		//SendSms.sendsms(number.getnum());
	   		return new ModelAndView("str", "mysent",SendSms.sendsms(number.getnum()));
	   
		   }
		   
   
   @RequestMapping(value="/chat",method=RequestMethod.GET)
   public ModelAndView chat1(@ModelAttribute("SpringWeb")Chat chat,ModelMap model)
   {
	   chathistory="Bot: Hi please enter your pickup location \n";
	   return new ModelAndView("chat","command",chathistory);
   }
   @RequestMapping(value="/chat",method=RequestMethod.POST)
   public ModelAndView chat2(@ModelAttribute("SpringWeb")Chat chat,ModelMap model) throws IOException
   {	
	   chathistory=chathistory+"\n\nYou:"+chat.getmessage();
	   
	   chathistory+="\n\nDectected Location : "+Locationapi.getlocation(chat.getmessage());
	   
	   if(chathistory.length()>42&&!(chathistory.contains(chat.str1)))
	   {
		   	//System.out.println(chat.getmessage());
		   
		    chathistory=chathistory.concat(chat.str1);
	   }
	   
	   if(chathistory.length()>250&&!(chathistory.contains(chat.str2)))
	   {
		   	chathistory=chathistory.concat(chat.str2);
		   	
	   }
	  
	   return new ModelAndView("chat","command",chathistory);
	   	
   }
   @RequestMapping(value = "/sendmail", method = RequestMethod.POST)
   public ModelAndView sendmail(@ModelAttribute("SpringWeb")Contact contact, 
		   ModelMap model) {
	   
	   String template="Dear Customer"+"\n"+"\n"+"Thank you for writing in!"+"\n"+"\n"+"Our customer support team will look into this and respond at the earliest."+"\n"+"\n"+"\n"+"Regards"+"\n"+"\n"+"Vihik cabs Customer Support Team";
	   
	   
	   
	   
	   SendMail.sendm(contact.getfname(),contact.getlname(),"support@vihik.com", contact.getsubject(), contact.getmessage());
	   SendMail.sendm("","",contact.getfrom(), contact.getsubject(), template);
      return new ModelAndView("str", "mysent", contact.getfrom()+" "+contact.getsubject()+" " +contact.getmessage());
	      }
   
   
   
  
   
   
   
}