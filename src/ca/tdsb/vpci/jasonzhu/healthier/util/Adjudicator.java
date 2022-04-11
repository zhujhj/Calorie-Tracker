package ca.tdsb.vpci.jasonzhu.healthier.util;

import ca.tdsb.vpci.jasonzhu.healthier.model.MealItem;
import ca.tdsb.vpci.jasonzhu.healthier.model.User;
import ca.tdsb.vpci.jasonzhu.healthier.model.WorkoutSession;

import java.util.ArrayList;

public class Adjudicator {
    private FileHandler fileHandler = new FileHandler();

    public double targetCalories(User user) {
        double weight = user.getWeight();
        double height = user.getHeight();
        double age = user.getAge();
        double targetCalories = 0;
        String gender = user.getGender();
        if (gender.equalsIgnoreCase("Male")) {
            targetCalories = 66 + (6.2 * weight) + (12.7 * height) - (6.76 * age);
        } else if (gender.equalsIgnoreCase("Female")) {
            targetCalories = 655.1 + (4.35 * weight) + (4.7 * height) - (4.7 * age);
        }
        return Math.round(targetCalories * 10) / 10.0;
    }

    public double calculateDailyCalories(ArrayList<MealItem> mealItems) {
        double calories = 0;
        for (MealItem m : mealItems) {
            if (m != null) {
                calories += m.getCalories() * m.getPortions();
            }
        }
        return calories;
    }

    public double calculateDailyCaloriesBurned(ArrayList<WorkoutSession> workoutSessions) {
        double caloriesBurned = 0;
        for (WorkoutSession w : workoutSessions) {
            if (w != null) {
                caloriesBurned += w.getCaloriesBurned() * w.getSessionTime();
            }
        }
        return caloriesBurned;
    }

    public double netCalories() {
        return Math.round((calculateDailyCalories(fileHandler.getMealItems()) - calculateDailyCaloriesBurned(fileHandler.getWorkoutSessions()) - Context.getUser().getTargetCalories()) * 10.0) / 10.0;
    }

    public String healthMessage(double netCalories) {
        if (netCalories < 100 && netCalories > 0) {
            return "You have gained a small amount of calories today, but this barely affects your weight.|To maintain your current weight, continue with this calorie count.|To lose weight, burn more than 100 calories a day.|To gain weight, take in more than 100 calories a day.";
        } else if (netCalories >= 100) {
            return "You gained a noticeable amount of calories today.|If you continue with this calorie count, you will gain weight.|To maintain your current weight, try to take in or burn less than 100 calories a day.|To lose weight, burn more than 100 calories each day.";
        } else if (netCalories > -100 && netCalories < 0) {
            return "You have lost small amount of calories today, but this barely affects your weight.|To maintain your current weight, continue with this calorie count.|To lose weight, burn more than 100 calories a day.|To gain weight, gain 100 or more calories a day.";
        } else if (netCalories <= -100) {
            return "You have lost a noticeable amount of calories today.|If you continue with this calorie count, you will lose weight.|To maintain your current weight, try to take in or burn less than 100 calories.|To gain weight, gain more than 100 calories each day.";
        } else {
            return "You have no gain or loss of calories today.|To maintain weight, continue this calorie count.|To lose weight, burn more than 100 calories each day.|To gain weight, gain more than 100 calories each day.";
        }
    }
}