package ca.tdsb.vpci.jasonzhu.healthier.model;

public class FoodItem {
    private double calories;
    private String name;

    public void setCalories(double d) {
        this.calories = d;
    }

    public double getCalories() {
        return this.calories;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public String toString() {
        return name + ", " + calories;
    }
}
