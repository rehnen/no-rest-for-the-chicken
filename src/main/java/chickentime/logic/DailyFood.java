package chickentime.logic;

/**
 * Created by marcu on 2017-03-05.
 */
public class DailyFood {

    public final String meat;
    public final String fish;
    public final String vegetarian;

    public DailyFood(String meat, String fish, String vegetarian) {
        this.meat = meat;
        this.fish = fish;
        this.vegetarian = vegetarian;
    }

    //Needed for JSON serialization with JAX-RS
    public DailyFood() {
        this.meat = "";
        this.fish = "";
        this.vegetarian = "";
    }

    public boolean equals(DailyFood other) {
        if(this.meat.equals(other.meat)
                && this.fish.equals(other.fish)
                && this.vegetarian.equals(other.vegetarian)) {
            return true;
        }
        else {
            return false;
        }
    }
}
