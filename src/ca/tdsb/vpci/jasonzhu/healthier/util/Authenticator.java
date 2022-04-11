package ca.tdsb.vpci.jasonzhu.healthier.util;

import ca.tdsb.vpci.jasonzhu.healthier.model.FoodItem;
import ca.tdsb.vpci.jasonzhu.healthier.model.User;
import ca.tdsb.vpci.jasonzhu.healthier.model.WorkoutItem;

import java.util.ArrayList;

public class Authenticator {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<FoodItem> foods = new ArrayList<FoodItem>();
    private ArrayList<WorkoutItem> workouts = new ArrayList<WorkoutItem>();
    private FileHandler fileHandler = new FileHandler();

    public String register(User user) {
        String username = user.getUsername();
        if (!user.getPassword().trim().equals(user.getConfirmPassword().trim())) {
            return "Unsuccessful register, passwords do not match.";
        }
        if (user.getUsername().equals("") || user.getPassword().equals("") || user.getConfirmPassword().equals("") || user.getGender().equals("Choose Gender")) {
            return "Unsuccessful register, empty fields.";
        }
        users = fileHandler.getUsers();
        if (users != null && users.size() > 0) {
            for (User u : users) {
                if (u != null) {
                    if (u.getUsername().trim().equals(username.trim()))
                        return "Unsuccessful register, username is already in system";
                }
            }
        }
        fileHandler.register(user);
        return "success";
    }

    public boolean login(String username, String password) {
        users = fileHandler.getUsers();
        for (User u : users) {
            if (u != null) {
                if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                    Context.setUser(u);
                    fileHandler.createFiles();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean addFoodItem(FoodItem foodItem) {
        String food = foodItem.getName();
        foods = fileHandler.getFoodItems();
        if (foods != null) {
            for (FoodItem f : foods) {
                if (f != null) {
                    if (f.getName().equals(food)) {
                        return false;
                    }
                }
            }
        }
        fileHandler.addFood(foodItem);
        return true;
    }

    public boolean addWorkoutItem(WorkoutItem workoutItem) {
        String workout = workoutItem.getName();
        workouts = fileHandler.getWorkoutItems();
        if (workouts != null) {
            for (WorkoutItem w : workouts) {
                if (w != null) {
                    if (w.getName().equals(workout)) {
                        return false;
                    }
                }
            }
        }
        fileHandler.addWorkout(workoutItem);
        return true;
    }
}
