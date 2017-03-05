package chickentime;

import chickentime.logic.DailyFood;
import chickentime.logic.FoodProcessor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.DayOfWeek;
import java.util.Map;

/**
 * Created by marcu on 2017-03-05.
 */
public class FoodProcessorTest {
    private String document = "";
    private ClassLoader classLoader;

    @Before
    public void beforeClass() throws Exception {
        classLoader = this.getClass().getClassLoader();
        document = readFile("utkiken.html");
    }


    @Test
    public void getFoodForMonday() {
        DailyFood dailyFood = FoodProcessor.getFoodForDay(document, DayOfWeek.MONDAY);
        Assert.assertTrue(dailyFood.equals(new DailyFood("Pork loin with gravy, vegetables and potato",
                "Alaska Pollock in red Curry sauce with rice",
                "Root Vegetable Gratin with Thyme")));
    }
    @Test
    public void getFoodForFriday() {
        DailyFood dailyFood = FoodProcessor.getFoodForDay(document, DayOfWeek.FRIDAY);
        Assert.assertTrue(dailyFood.equals(new DailyFood("Hamburger with fries, Aioli and sides",
                "Fish Soup with Shrimps",
                "Omelet with Potato and Tomato")));
    }

    @Test
    public void getFullWeek() {
        Map<DayOfWeek, DailyFood> week = FoodProcessor.getFoodMenu(document);
        DailyFood expectedDailyFood = new DailyFood("Hamburger with fries, Aioli and sides",
                "Fish Soup with Shrimps",
                "Omelet with Potato and Tomato");
        Assert.assertTrue(expectedDailyFood.equals(week.get(DayOfWeek.FRIDAY)));
    }

    @Test
    public void getThisWeeksSpecial() {
        String message = FoodProcessor.getThisWeeksSpecial(document);
        Assert.assertEquals(message, "Pasta Carbonara");
    }

    private String readFile(String fileName) throws Exception{
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        StringBuilder sb = new StringBuilder();

        String line;
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
