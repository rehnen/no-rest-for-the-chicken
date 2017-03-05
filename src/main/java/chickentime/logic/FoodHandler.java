package chickentime.logic;

import java.time.DayOfWeek;
import java.util.Map;

/**
 * Created by marcu on 2017-03-05.
 */
public class FoodHandler {

    private static FoodHandler instance;
    private Map<DayOfWeek, DailyFood> foodsOfDay;
    private String foodOfTheWeek;

    private FoodHandler() {

    }

    public static FoodHandler getInstance() {
        return instance;
    }

    public FoodHandler putFoodOfDay(DayOfWeek dayOfWeek, DailyFood food) {
        this.foodsOfDay.put(dayOfWeek, food);
        return this;
    }

    public FoodHandler setFoodOfWeekThe(String foodOfTheWeek) {
        this.foodOfTheWeek = foodOfTheWeek;
        return this;
    }

    public String getFoodOfTheWeek() {
        return this.foodOfTheWeek;
    }

    public DailyFood getFoodOfTheDay(DayOfWeek dayOfWeek) {
        return this.foodsOfDay.get(dayOfWeek);
    }


}
