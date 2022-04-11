package ca.tdsb.vpci.jasonzhu.healthier.model;

public class WorkoutItem {

    private double caloriesBurned;
    private String name;

    public void setCaloriesBurned(double d) {
        this.caloriesBurned = d;
    }

    public double getCaloriesBurned() {
        return this.caloriesBurned;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return name + ", " + caloriesBurned;
    }

}
