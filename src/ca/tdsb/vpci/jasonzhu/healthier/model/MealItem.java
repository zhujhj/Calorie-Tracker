package ca.tdsb.vpci.jasonzhu.healthier.model;

public class MealItem {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;
    private String meal;
    private double calories;
    private double portions;

    public MealItem(FoodItem foodItem) {
        name = foodItem.getName();
        calories = foodItem.getCalories();
    }

    public MealItem() {
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public double getPortions() {
        return this.portions;
    }

    public void setPortions(double d) {
        this.portions = d;
    }

    public String toString() {
        return name + ", " + calories + ", " + meal + ", " + portions;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getCalories() {
        return calories;
    }
}
