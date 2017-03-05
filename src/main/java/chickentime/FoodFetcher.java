package chickentime;

import chickentime.logic.DailyFood;
import chickentime.logic.FoodHandler;
import chickentime.logic.FoodProcessor;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.DayOfWeek;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by marcu on 2017-03-05.
 */
@WebListener
public final class FoodFetcher implements ServletContextListener {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        logger.log(Level.INFO, "Was able to run this class on startup!");

        HttpURLConnection connection = null;

        try {
            URL url = new URL("http://www.utkikenkarlskrona.se/ericsson/");
            connection = (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            logger.log(Level.SEVERE, e.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.toString());
        }

        try {
            InputStream in = new BufferedInputStream(connection.getInputStream());
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            Map<DayOfWeek, DailyFood> week = FoodProcessor.getFoodMenu(sb.toString());
            FoodHandler.getInstance()
                    .putFoodOfDay(DayOfWeek.MONDAY, week.get(DayOfWeek.MONDAY))
                    .putFoodOfDay(DayOfWeek.TUESDAY, week.get(DayOfWeek.TUESDAY))
                    .putFoodOfDay(DayOfWeek.WEDNESDAY, week.get(DayOfWeek.WEDNESDAY))
                    .putFoodOfDay(DayOfWeek.THURSDAY, week.get(DayOfWeek.THURSDAY))
                    .putFoodOfDay(DayOfWeek.FRIDAY, week.get(DayOfWeek.FRIDAY));

            FoodHandler.getInstance().setFoodOfTheWeek(FoodProcessor.getThisWeeksSpecial(sb.toString()));
            logger.log(Level.INFO, FoodHandler.getInstance().toString());
        } catch (MalformedURLException e) {
            logger.log(Level.SEVERE, e.toString());
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.toString());
        } finally {
            connection.disconnect();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
