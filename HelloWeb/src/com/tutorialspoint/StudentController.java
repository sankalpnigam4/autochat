package com.tutorialspoint;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.ModelMap;



@Controller
public class StudentController {

   @RequestMapping(value = "/contact", method = RequestMethod.GET)
   public ModelAndView contact() {
      return new ModelAndView("contact", "command", new Contact());
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