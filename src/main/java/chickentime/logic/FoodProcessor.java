package chickentime.logic;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by marcu on 2017-03-05.
 */
public final class FoodProcessor {


    public static Map<DayOfWeek, DailyFood> getFoodMenu(String document) {
        Map<DayOfWeek, DailyFood> menu = new HashMap<>();
        menu.put(DayOfWeek.MONDAY, getFoodForDay(document, DayOfWeek.MONDAY));
        menu.put(DayOfWeek.TUESDAY, getFoodForDay(document, DayOfWeek.TUESDAY));
        menu.put(DayOfWeek.WEDNESDAY, getFoodForDay(document, DayOfWeek.WEDNESDAY));
        menu.put(DayOfWeek.THURSDAY, getFoodForDay(document, DayOfWeek.THURSDAY));
        menu.put(DayOfWeek.FRIDAY, getFoodForDay(document, DayOfWeek.FRIDAY));
        menu.put(DayOfWeek.SATURDAY, getFoodForDay(document, DayOfWeek.SATURDAY));
        menu.put(DayOfWeek.SUNDAY, getFoodForDay(document, DayOfWeek.SUNDAY));

        return menu;
    }

    public static String getThisWeeksSpecial(String document) {

        int specialIndex = document.indexOf("Lunch menu");
        String specialContainer = document.substring(specialIndex);
        specialContainer = specialContainer.substring(specialContainer.indexOf("<br />") + 6, specialContainer.indexOf("</p>"));


        return specialContainer.trim();
    }

    public static DailyFood getFoodForDay(String document, DayOfWeek day) {
        if(day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)) {
            return new DailyFood("Closed every " + day.toString(), "", "");
        }
        String dayString = day.toString().toLowerCase().substring(0,1).toUpperCase()
                + day.toString().toLowerCase().substring(1);
        int dayLocation = document.indexOf("<p><b>"+ dayString +"</b><br />");
        String restOfDoc = document.substring(dayLocation);
        String foodString = restOfDoc.substring(restOfDoc.indexOf("-") +1, restOfDoc.indexOf("</p>"));
        foodString = foodString.replaceAll("<br />", "");
        String options[] = foodString.split("-");
        return new DailyFood(options[0].trim(), options[1].trim(), options[2].trim());
    }
}