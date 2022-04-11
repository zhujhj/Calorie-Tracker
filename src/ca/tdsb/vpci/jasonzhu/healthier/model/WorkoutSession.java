package ca.tdsb.vpci.jasonzhu.healthier.model;

public class WorkoutSession {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public double getCaloriesBurned() {
        return caloriesBurned;
    }

    public void setCaloriesBurned(double caloriesBurned) {
        this.caloriesBurned = caloriesBurned;
    }

    private double caloriesBurned;
    private double sessionTime;

    public WorkoutSession(WorkoutItem workoutItem) {
        name = workoutItem.getName();
        caloriesBurned = workoutItem.getCaloriesBurned();
    }

    public WorkoutSession() {
    }

    public double getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(double sessionTime) {
        this.sessionTime = sessionTime;
    }

    public String toString() {
        return name + ", " + caloriesBurned + ", " + sessionTime;
    }
}
