package com.bathproceduresscheduling.utility;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * 
 * @author nikolas
 * Class for converting String data to normal date
 * input String
 * output Date
 *
 */
@Component
@SessionScope
public class StringToDateConverter {

	
	// String -> Date
	public Date StringToDate(String stringDate) throws ParseException, NullPointerException {
		// Ilyen formatumu Stringet konvertaljuk realis datumma
		SimpleDateFormat stringDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startOfProceduresStrDateConvertToRealDate = stringDateFormat.parse(stringDate);
		return startOfProceduresStrDateConvertToRealDate;
	}

	// String -> Day
	public String StringToDay(String stringDate) throws ParseException, NullPointerException {
		// A datumbol kivesszuk hogy milyen nap
		DateFormat dateToDay = new SimpleDateFormat("EEEE");
		// A nap, csak az a baj, h szlovakul van, olyan nyelven jelenik meg amilyen nyelvu windows van a gepen
		String finalDay = dateToDay.format(StringToDate(stringDate));
		return finalDay;
	}
	
	
	// String -> DayOfWeekInt
	public int StringToDateInt(String stringDate) throws ParseException, NullPointerException {
		Calendar c = Calendar.getInstance();
		c.setTime(StringToDate(stringDate));
		int realDateConvertDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int dayOfWeekInt = realDateConvertDayOfWeek - 1;
		return dayOfWeekInt;
	}
	
	
	//String -> DayOfWeekInHungarian
    public String StringToDayInHungarian(String stringDate) throws ParseException, NullPointerException {
        Calendar c = Calendar.getInstance();
        c.setTime(StringToDate(stringDate));
        int realDateConvertDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        int dayOfWeekInt = realDateConvertDayOfWeek;
        String DayInHungarian="";

        switch (dayOfWeekInt) {
            case 2:
                DayInHungarian = "Hétfő";
                break;
            case 3:
                DayInHungarian = "Kedd";
                break;
            case 4:
                DayInHungarian = "Szerda";
                break;
            case 5:
                DayInHungarian = "Csütörtök";
                break;
            case 6:
                DayInHungarian = "Péntek";
                break;
            case 7:
                DayInHungarian = "Szombat";
                break;
            case 1:
                DayInHungarian = "Vasárnap";
                break;
        }

        return DayInHungarian;
    }
    
    
    
	// String -> DayOfWeekInHungarian
	public String StringToDayInHungarianAddDaysFucntion(String stringDate, int day) throws ParseException, NullPointerException {

		Calendar c = Calendar.getInstance();
		c.setTime(StringToDate(stringDate));
		// Adding days
		c.add(Calendar.DAY_OF_WEEK, day);
		int realDateConvertDayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		int dayOfWeekInt = realDateConvertDayOfWeek;

		// Convert to hungarian
		String DayInHungarian = "";

		switch (dayOfWeekInt) {
		case 2:
			DayInHungarian = "Hétfő";
			break;
		case 3:
			DayInHungarian = "Kedd";
			break;
		case 4:
			DayInHungarian = "Szerda";
			break;
		case 5:
			DayInHungarian = "Csütörtök";
			break;
		case 6:
			DayInHungarian = "Péntek";
			break;
		case 7:
			DayInHungarian = "Szombat";
			break;
		case 1:
			DayInHungarian = "Vasárnap";
			break;
		}

		return DayInHungarian;
	}
	
	
	//String -> Date plus day
    public String StringToDateAddDaysFunction (String stringDate, int day) throws ParseException {

        Calendar c = Calendar.getInstance();
        c.setTime(StringToDate(stringDate));
        //Adding days
        c.add(Calendar.DAY_OF_WEEK, day);
        
        //Datumot csinalunk a c-bol
        Date cDate = c.getTime();
        
        SimpleDateFormat stringDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateAddDays = stringDateFormat.format(cDate);

        return dateAddDays;
    }
    
    
    //Get back random int 
    public static int randomWithRange(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
    
    
    
    
    
    
    
    
    
    
	

}
