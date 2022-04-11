package ca.tdsb.vpci.jasonzhu.healthier.util;

import ca.tdsb.vpci.jasonzhu.healthier.model.*;

import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class FileHandler {
    private RandomAccessFile userFile;
    private RandomAccessFile foodItemFile;
    private RandomAccessFile workoutItemFile;
    private RandomAccessFile foodDateFile;
    private RandomAccessFile workoutDateFile;

    public FileHandler() {
        try {
            userFile = new RandomAccessFile("users.txt", "rw");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void register(User user) {
        try {
            userFile.writeBytes(user.toString());
            userFile.writeBytes(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createFiles() {
        try {
            foodItemFile = new RandomAccessFile("food" + Context.getUser().getUsername() + ".txt", "rw");
            workoutItemFile = new RandomAccessFile("workout" + Context.getUser().getUsername() + ".txt", "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addFood(FoodItem foodItem) {
        try {
            foodItemFile = new RandomAccessFile("food" + Context.getUser().getUsername() + ".txt", "rw");
            foodItemFile.seek(foodItemFile.length());
            foodItemFile.writeBytes(foodItem.toString());
            foodItemFile.writeBytes(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addWorkout(WorkoutItem workoutItem) {
        try {
            workoutItemFile = new RandomAccessFile("workout" + Context.getUser().getUsername() + ".txt", "rw");
            workoutItemFile.seek(workoutItemFile.length());
            workoutItemFile.writeBytes(workoutItem.toString());
            workoutItemFile.writeBytes(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createDateFile() {
        try {
            foodDateFile = new RandomAccessFile(Context.getUser().getUsername() + Context.getMonth() +
                    Context.getDayOfMonth() + Context.getYear() + "food" + ".txt", "rw");
            workoutDateFile = new
                    RandomAccessFile(Context.getUser().getUsername() + Context.getMonth() + Context.getDayOfMonth() + Context.getYear() + "workout" + ".txt", "rw");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseFood(MealItem mealItem) {
        try {
            foodDateFile = new RandomAccessFile(Context.getUser().getUsername() + Context.getMonth() +
                    Context.getDayOfMonth() + Context.getYear() + "food" + ".txt", "rw");
            foodDateFile.seek(foodDateFile.length());
            foodDateFile.writeBytes(mealItem.toString());
            foodDateFile.writeBytes(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void chooseWorkout(WorkoutSession workoutSession) {
        try {
            workoutDateFile = new RandomAccessFile(Context.getUser().getUsername() + Context.getMonth() +
                    Context.getDayOfMonth() + Context.getYear() + "workout" + ".txt", "rw");
            workoutDateFile.seek(workoutDateFile.length());
            workoutDateFile.writeBytes(workoutSession.toString());
            workoutDateFile.writeBytes(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void printMealItems(List list) {
        ArrayList<MealItem> mealItems = getMealItems();
        for (MealItem m : mealItems) {
            if (m != null)
                list.add(m.getMeal() + ": " + m.getName() + " (" +
                        m.getCalories() + " Calories / portion)" + " x " + m.getPortions());
        }
    }

    public void printWorkoutSessions(List list) {
        ArrayList<WorkoutSession> workoutSessions = getWorkoutSessions();
        for (WorkoutSession w : workoutSessions) {
            if (w != null)
                list.add(w.getName() + " (" + w.getCaloriesBurned() + "Calories Burned / hour) " + w.getSessionTime() + " hours");
        }
    }

    public ArrayList<User> getUsers() {
        String[] strings = new String[9];
        ArrayList<User> users = new ArrayList<User>();
        String user = null;
        try {
            userFile.seek(0);
            while ((user = userFile.readLine()) != null) {
                if (!user.isEmpty()) {
                    strings = user.split(", ");
                    User newUser = new User();
                    newUser.setUsername(strings[0].trim());
                    newUser.setPassword(strings[1]);
                    newUser.setConfirmPassword(strings[2]);
                    newUser.setAge(Double.parseDouble(strings[3]));
                    newUser.setWeight(Double.parseDouble(strings[4]));
                    newUser.setHeight(Double.parseDouble(strings[5]));
                    newUser.setGender(strings[6]);
                    newUser.setTargetCalories(Double.parseDouble(strings[7]));
                    users.add(newUser);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            e.getMessage();
        }
        return users;
    }

    public ArrayList<FoodItem> getFoodItems() {
        String[] strings = new String[4];
        ArrayList<FoodItem> foods = new ArrayList<FoodItem>();
        String food;
        try {
            RandomAccessFile main = new RandomAccessFile("food" +
                    Context.getUser().getUsername() + ".txt", "r");
            do {
                food = main.readLine();
                if (food != null) {
                    strings = food.split(", ");
                    FoodItem foodItem = new FoodItem();
                    foodItem.setName(strings[0]);
                    foodItem.setCalories(Double.parseDouble(strings[1]));
                    foods.add(foodItem);
                }
            } while (food != null);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return foods;
    }

    public ArrayList<WorkoutItem> getWorkoutItems() {
        String[] strings = new String[2];
        ArrayList<WorkoutItem> workouts = new ArrayList<WorkoutItem>();
        String workout;
        try {
            RandomAccessFile main = new RandomAccessFile("workout" +
                    Context.getUser().getUsername() + ".txt", "r");
            do {
                workout = main.readLine();
                if (workout != null) {
                    strings = workout.split(", ");
                    WorkoutItem workoutItem = new WorkoutItem();
                    workoutItem.setName(strings[0]);
                    workoutItem.setCaloriesBurned(Double.parseDouble(strings[1]));
                    workouts.add(workoutItem);
                }
            } while (workout != null);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return workouts;
    }

    public ArrayList<MealItem> getMealItems() {
        String[] strings = new String[4];
        ArrayList<MealItem> mealItems = new ArrayList<MealItem>();
        String mealItem;
        try {
            RandomAccessFile main = new RandomAccessFile(Context.getUser().getUsername() + Context.getMonth() +
                    Context.getDayOfMonth() + Context.getYear() + "food" + ".txt", "r");
            do {
                mealItem = main.readLine();
                if (mealItem != null) {
                    strings = mealItem.split(", ");
                    MealItem meal = new MealItem();
                    meal.setName(strings[0]);
                    meal.setCalories(Double.parseDouble(strings[1]));
                    meal.setMeal(strings[2]);
                    meal.setPortions(Double.parseDouble(strings[3]));
                    mealItems.add(meal);
                }
            } while (mealItem != null);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return mealItems;
    }

    public ArrayList<WorkoutSession> getWorkoutSessions() { String[] strings = new String[3]; ArrayList<WorkoutSession> workoutSessions = new
            ArrayList<WorkoutSession>();
        String workoutSession;
        try {
            RandomAccessFile main = new
                    RandomAccessFile(Context.getUser().getUsername() + Context.getMonth() + Context.getDayOfMonth() + Context.getYear() + "workout" + ".txt", "r");
            do {
                workoutSession = main.readLine(); if (workoutSession != null) {
                    strings = workoutSession.split(", "); WorkoutSession session = new WorkoutSession(); session.setName(strings[0]);
                    session.setCaloriesBurned(Double.parseDouble(strings[1])); session.setSessionTime(Double.parseDouble(strings[2]));
                    workoutSessions.add(session);
                } } while (workoutSession != null);
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
        return workoutSessions;
    }

}
