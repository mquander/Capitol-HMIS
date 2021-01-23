package com.hmis;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import javax.servlet.http.HttpServlet;

@SuppressWarnings("serial")
public class HomeServlet extends HttpServlet {
	
	public boolean stringIsInt(String str) {
		boolean returnValue;
		int intValue;
		  try {

            intValue = Integer.parseInt(str);
            returnValue = true;
        }catch(NumberFormatException nfe) {
            
            returnValue = false;
        }
		  return returnValue;
	  }
	
	public boolean dateFormatOK(String date) {
		boolean returnValue;
		
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;
		try {
			LocalDate.parse(date, dtf);
			returnValue = true;
		}catch(DateTimeParseException dtpe) {
			returnValue = false;
		}
        return returnValue;
	}
}
