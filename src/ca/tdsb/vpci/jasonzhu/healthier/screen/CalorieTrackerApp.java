package ca.tdsb.vpci.jasonzhu.healthier.screen;

import ca.tdsb.vpci.jasonzhu.healthier.model.*;
import ca.tdsb.vpci.jasonzhu.healthier.util.*;

import java.awt.*;
import java.util.ArrayList;

public class CalorieTrackerApp extends EasyApp {

    private CalendarDate calendarDate = new CalendarDate();
    private Authenticator a = new Authenticator();
    private Adjudicator adjudicator = new Adjudicator();
    private FileHandler fileHandler = new FileHandler();
    private boolean fromRegister = false;
    private boolean fromAddFood = false;
    private boolean fromAddWorkout = false;
    private boolean fromChooseFood = false;
    private boolean fromChooseWorkout = false;
    private boolean fromCalendar = false;
    private boolean calendarFromLogin = false;
    private boolean calendarFromDiet = false;

    //HOME
    private Label title_welcome = addLabel("Welcome to your Calorie Tracker!", 50, 50, 200, 50, this);
    private Button newUser = addButton("New User", 50, 100, 100, 50, this);
    private Button returningUser = addButton("Returning User", 250, 100, 100, 50, this);

    //REGISTER
    private Label title_register;
    private Button register_confirm;
    private Label username;
    private Label password;
    private Label confirmPassword;
    private Label weight;
    private Label height;
    private Label age;
    private Label gender;
    private TextField usernameBox;
    private TextField passwordBox;
    private TextField confirmPasswordBox;
    private TextField weightBox;
    private TextField heightBox;
    private TextField ageBox;
    private Choice genderBox;
    //LOGIN
    private Button login_confirm;
    private Label login_username;
    private Label login_password;
    private TextField usernameLoginBox;
    private TextField passwordLoginBox;
    private Label title_login;
    //DIET SCREEN
    private Label title_diet;
    private Label title_diet_workout;
    private Label title_diet_health;
    private Label diet_date;
    private Button calendarButton;
    private Button addFood;
    private Button chooseFood;
    private Button chooseWorkout;
    private List dailyFoods;
    private List dailyWorkouts;
    private List caloriesFromFood;
    private List caloriesBurnedFromWorkout;
    private List healthReport;
    //ADD FOOD
    private Label title_addFood;
    private Label foodName;
    private Label calories;
    private Button addFoodConfirm;
    private Button foodBack;
    private TextField foodNameBox;
    private TextField caloriesBox;
    //CALENDAR
    private ArrayList<Button> days = new ArrayList<Button>();
    private Button prevMonth;
    private Button nextMonth;
    private Label title_calendar;
    private int dateFromButton;
    private Label weekdays;
    //ADD WORKOUT
    private Button addWorkoutItem;
    private Button addWorkoutConfirm;
    private Button workoutBack;
    private Label title_addWorkout;
    private Label caloriesBurned;
    private Label workoutName;
    private TextField caloriesBurnedBox;
    private TextField workoutNameBox;
    //CHOOSE FOOD
    private Label title_chooseFood;
    private Button chooseFoodConfirm;
    private Button chooseFoodBack;
    private Choice choiceOfFood;
    private Choice meal;
    private Label portionAmount;
    private TextField portionAmountBox;
    private ArrayList<FoodItem> foods = new ArrayList<FoodItem>();
    private String foodNames;
    //CHOOSE WORKOUT
    private Label title_chooseWorkout;
    private Button chooseWorkoutConfirm;
    private Button chooseWorkoutBack;
    private Choice choiceOfWorkout;
    private Label workoutDuration;
    private TextField workoutDurationBox;
    private ArrayList<WorkoutItem> workouts = new ArrayList<WorkoutItem>();
    private String workoutNames;

    public CalorieTrackerApp() {
        setTitle("Calorie Tracker");
        setSize(400, 200);
    }

    public void actions(Object source, String command) {
        if (source == newUser) {
            changeToRegisterScreen();
        }
        if (source == returningUser) {
            changeToLoginScreen();
        }
        if (source == register_confirm) {
            boolean isEmpty = false;
            User user = new User();
            user.setUsername(usernameBox.getText());
            user.setPassword(passwordBox.getText());
            user.setConfirmPassword(confirmPasswordBox.getText());
            try {
                user.setWeight(Double.parseDouble(weightBox.getText()));
                user.setHeight(Double.parseDouble(heightBox.getText()));
                user.setAge(Double.parseDouble(ageBox.getText()));
            } catch (NumberFormatException e) {
                output("Unsuccessful register, enter a number for weight, height, and age.");
                isEmpty = true;
            }
            try {
                user.setGender(genderBox.getSelectedItem());
            } catch (NullPointerException e) {
                output("Unsuccessful register, empty fields");
                isEmpty = true;
            }
            user.setTargetCalories(adjudicator.targetCalories(user));
            if (isEmpty) {
            } else {
                String register = a.register(user);
                if (register.equals("success")) {
                    changeToLoginScreen();
                } else {
                    output(register);
                }
            }
        }
        if (source == login_confirm) {
            if (a.login(usernameLoginBox.getText(), passwordLoginBox.getText())) {
                calendarFromLogin = true;
                changeToCalendarScreen();
            } else {
                output("Unsuccessful log-in, username or password incorrect.");
            }
        }
        if (source == addFood) {
            changeToAddFoodScreen();
        }
        if (source == addFoodConfirm) {
            boolean errorMessage = false;
            FoodItem foodItem = new FoodItem();
            if (!foodNameBox.getText().equals(""))
                foodItem.setName(foodNameBox.getText());
            else {
                output("Please enter a name for the food.");
                errorMessage = true;
            }
            if (!errorMessage) {
                try {
                    foodItem.setCalories(Integer.parseInt(caloriesBox.getText()));
                } catch (NumberFormatException e) {
                    output("Please enter a number for calories.");
                    errorMessage = true;
                }
            }
            if (!errorMessage) {
                if (!a.addFoodItem(foodItem)) {
                    output("Food is already in system. Use a different name.");
                } else {
                    fromAddFood = true;
                    changeToDietScreen();
                }
            }
        }
        if (source == addWorkoutItem) {
            changeToAddWorkoutItemScreen();
        }
        if (source == addWorkoutConfirm) {
            boolean errorMessage = false;
            WorkoutItem workoutItem = new WorkoutItem();
            if (!workoutNameBox.getText().equals(""))
                workoutItem.setName(workoutNameBox.getText());
            else {
                output("Please enter a name for the workout.");
                errorMessage = true;
            }
            if (!errorMessage) {
                try {
                    workoutItem.setCaloriesBurned(Integer.parseInt(caloriesBurnedBox.getText()));
                } catch (NumberFormatException e) {
                    output("Please enter a number for calories burned.");
                    errorMessage = true;
                }
            }
            if (!errorMessage) {
                if (!a.addWorkoutItem(workoutItem)) {
                    output("Workout is already in system. Use a different name.");
                } else {
                    fromAddWorkout = true;
                    changeToDietScreen();
                }
            }
        }
        if (source == calendarButton) {
            calendarFromDiet = true;
            changeToCalendarScreen();
        }
        if (source == foodBack) {
            fromAddFood = true;
            changeToDietScreen();
        }
        if (source == workoutBack) {
            fromAddWorkout = true;
            changeToDietScreen();
        }
        if (source == days) {
            changeToDietScreen();
        }
        if (source == chooseFood) {
            changeToChooseFoodScreen();
        }
        if (source == chooseWorkout) {
            changeToChooseWorkoutScreen();
        }
        if (source == chooseFoodBack) {
            fromChooseFood = true;
            changeToDietScreen();
        }
        if (source == chooseWorkoutBack) {
            fromChooseWorkout = true;
            changeToDietScreen();
        }
        for (int i = 0; i < days.size(); i++) {
            if (source == days.get(i)) {
                dateFromButton = i + 1;
                Context.setDayOfMonth(dateFromButton);
                Context.setMonth(calendarDate.getMonth());
                Context.setYear(calendarDate.getYear());
                fileHandler.createDateFile();
                fromCalendar = true;
                changeToDietScreen();
            }
        }
        if (source == chooseFoodConfirm) {
            for (FoodItem f : foods) {
                if (f != null) {
                    boolean errorMessage = false;
                    if (choiceOfFood.getSelectedItem().equals(f.getName())) {
                        MealItem mealItem = new MealItem(f);
                        if (!meal.getSelectedItem().equals("Choose Meal")) {
                            mealItem.setMeal(meal.getSelectedItem());
                            try {
                                mealItem.setPortions(Double.parseDouble(portionAmountBox.getText()));
                            } catch (NumberFormatException e) {
                                output("Please enter a number for portion amount.");
                                errorMessage = true;
                            }
                        } else {
                            output("Please select a meal.");
                            errorMessage = true;
                        }
                        if (!errorMessage) {
                            fileHandler.chooseFood(mealItem);
                            fromChooseFood = true;
                            changeToDietScreen();
                        }
                    } else if (choiceOfFood.getSelectedItem().equals("Choose Food")) {
                        output("Please choose a food.");
                        break;
                    }
                }
            }
        }
        if (source == chooseWorkoutConfirm) {
            for (WorkoutItem w : workouts) {
                if (w != null) {
                    boolean errorMessage = false;
                    if (choiceOfWorkout.getSelectedItem().equals(w.getName())) {
                        WorkoutSession workoutSession = new WorkoutSession(w);
                        try {
                            workoutSession.setSessionTime(Double.parseDouble(workoutDurationBox.getText()));
                        } catch (NumberFormatException e) {
                            output("Please enter a session duration (hours).");
                            errorMessage = true;
                        }
                        if (!errorMessage) {
                            fileHandler.chooseWorkout(workoutSession);
                            fromChooseWorkout = true;
                            changeToDietScreen();
                        }
                    } else if (choiceOfWorkout.getSelectedItem().equals("Choose Workout")) {
                        output("Please choose a workout.");
                        break;
                    }
                }
            }
        }
        if (source == prevMonth) {
            calendarDate.setToPrevMonth();
            changeToCalendarScreen();
        }
        if (source == nextMonth) {
            calendarDate.setToNextMonth();
            changeToCalendarScreen();
        }
    }

    public void changeToRegisterScreen() {
        title_register = addLabel("Register Section", 50, 50, 200, 50, this);
        username = addLabel("Username: ", 50, 100, 100, 50, this);
        usernameBox = addTextField("", 175, 100, 175, 40, this);
        password = addLabel("Password: ", 50, 150, 100, 50, this);
        passwordBox = addTextField("", 175, 150, 175, 40, this);
        confirmPassword = addLabel("Confirm Password: ", 50, 200, 120, 50, this);
        confirmPasswordBox = addTextField("", 175, 200, 175, 40, this);
        weight = addLabel("Weight (lbs): ", 50, 250, 120, 50, this);
        weightBox = addTextField("", 175, 250, 175, 40, this);
        height = addLabel("Height (inches): ", 50, 300, 120, 50, this);
        heightBox = addTextField("", 175, 300, 175, 40, this);
        age = addLabel("Age: ", 50, 350, 120, 50, this);
        ageBox = addTextField("", 175, 350, 175, 40, this);
        gender = addLabel("Gender: ", 50, 400, 120, 50, this);
        genderBox = addChoice("Choose Gender|Male|Female", 175, 415, 175, 40, this);
        register_confirm = addButton("Confirm", 300, 450, 50, 40, this);
        setTitle("Register");
        setSize(400, 525);
        usernameBox.setFont(new Font("Arial", 0, 20));
        passwordBox.setFont(new Font("Arial", 0, 20));
        confirmPasswordBox.setFont(new Font("Arial", 0, 20));
        weightBox.setFont(new Font("Arial", 0, 20));
        heightBox.setFont(new Font("Arial", 0, 20));
        ageBox.setFont(new Font("Arial", 0, 20));
        genderBox.setFont(new Font("Arial", 0, 20));
        title_register.setFont(new Font("Arial", 0, 20));
        title_welcome.removeNotify();
        newUser.removeNotify();
        returningUser.removeNotify();
        fromRegister = true;
    }

    public void changeToLoginScreen() {
        if (fromRegister) {
            title_login = addLabel("Log-In Section", 50, 50, 200, 50, this);
            login_confirm = addButton("Confirm", 300, 200, 50, 40, this);
            login_username = addLabel("Username: ", 50, 100, 100, 50, this);
            usernameLoginBox = addTextField("", 175, 100, 175, 40, this);
            login_password = addLabel("Password: ", 50, 150, 100, 50, this);
            passwordLoginBox = addTextField("", 175, 150, 175, 40, this);
            setTitle("Login");
            setSize(400, 275);
            title_login.setFont(new Font("Arial", 0, 20));
            usernameLoginBox.setFont(new Font("Arial", 0, 20));
            passwordLoginBox.setFont(new Font("Arial", 0, 20));
            title_welcome.removeNotify();
            title_register.removeNotify();
            username.removeNotify();
            password.removeNotify();
            usernameBox.removeNotify();
            passwordBox.removeNotify();
            confirmPasswordBox.removeNotify();
            weightBox.removeNotify();
            heightBox.removeNotify();
            confirmPassword.removeNotify();
            weight.removeNotify();
            height.removeNotify();
            age.removeNotify();
            gender.removeNotify();
            ageBox.removeNotify();
            genderBox.removeNotify();
            register_confirm.removeNotify();
        } else {
            title_login = addLabel("Log-In Section", 50, 50, 200, 50, this);
            login_confirm = addButton("Confirm", 300, 200, 50, 40, this);
            login_username = addLabel("Username: ", 50, 100, 100, 50, this);
            usernameLoginBox = addTextField("", 175, 100, 175, 40, this);
            login_password = addLabel("Password: ", 50, 150, 100, 50, this);
            passwordLoginBox = addTextField("", 175, 150, 175, 40, this);
            setTitle("Login");
            setSize(400, 275);
            title_login.setFont(new Font("Arial", 0, 20));
            usernameLoginBox.setFont(new Font("Arial", 0, 20));
            passwordLoginBox.setFont(new Font("Arial", 0, 20));
            title_welcome.removeNotify();
            newUser.removeNotify();
            returningUser.removeNotify();
        }
    }

    public void changeToCalendarScreen() {
        if (prevMonth != null) {
            prevMonth.removeNotify();
        }
        if (nextMonth != null) {
            nextMonth.removeNotify();
        }
        prevMonth = addButton("<", 10, 30, 25, 25, this);
        nextMonth = addButton(">", 665, 30, 25, 25, this);

        if (title_calendar != null) {
            title_calendar.removeNotify();
        }
        if (weekdays != null) {
            weekdays.removeNotify();
        }
        title_calendar = addLabel(calendarDate.getMonth() + " " + calendarDate.getYear(), 290, 30, 200, 25, this);
        weekdays = addLabel(Context.getWeekdays(), 35, 55, 700, 45, this);

        int firstDayCounter = 1;
        int currentDayCounter = 1;

        if (days != null) {
            for (Button day : days) {
                day.removeNotify();
            }
        }

        days = new ArrayList<>();

        out:
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (firstDayCounter >= calendarDate.getWeekdayOfFirstDay()) {
                    days.add(addButton(String.valueOf(currentDayCounter), j * 100, 100 + i * 100, 100, 100, this));
                    currentDayCounter++;
                }
                if (currentDayCounter > calendarDate.getDays()) {
                    break out;
                }
                firstDayCounter++;
            }
        }
        setTitle("Calendar");
        setSize(700, 700);
        title_calendar.setFont(new Font("Arial", 0, 20));
        weekdays.setFont(new Font("Arial", 0, 15));
        if (calendarFromLogin) {
            title_login.removeNotify();
            login_confirm.removeNotify();
            login_username.removeNotify();
            usernameLoginBox.removeNotify();
            login_password.removeNotify();
            passwordLoginBox.removeNotify();
        } else if (calendarFromDiet) {
            title_diet.removeNotify();
            diet_date.removeNotify();
            dailyFoods.removeNotify();
            calendarButton.removeNotify();
            addFood.removeNotify();
            addWorkoutItem.removeNotify();
            chooseFood.removeNotify();
            chooseWorkout.removeNotify();
            title_diet_workout.removeNotify();
            dailyWorkouts.removeNotify();
            caloriesFromFood.removeNotify();
            caloriesBurnedFromWorkout.removeNotify();
            title_diet_health.removeNotify();
            healthReport.removeNotify();
        }
        calendarFromLogin = false;
        calendarFromDiet = false;
    }

    public void changeToDietScreen() {
        double netCalories = adjudicator.netCalories();
        String healthMessage = adjudicator.healthMessage(netCalories);
        diet_date = addLabel(calendarDate.getMonth() + " " + dateFromButton + ", " + calendarDate.getYear(), 50, 45, 200, 50, this);
        title_diet = addLabel("Today's Foods", 50, 100, 200, 50, this);
        title_diet_workout = addLabel("Today's Workouts", 325, 100, 200, 50, this);
        title_diet_health = addLabel("Health Report", 50, 400, 200, 50, this);
        addFood = addButton("Add New Food", 625, 100, 125, 40, this);
        chooseFood = addButton("Choose Food", 625, 150, 125, 40, this);
        addWorkoutItem = addButton("Add New Workout", 625, 200, 125, 40, this);
        chooseWorkout = addButton("Choose Workout", 625, 250, 125, 40, this);
        calendarButton = addButton("Calendar", 625, 300, 125, 40, this);
        dailyFoods = addList("", 50, 150, 250, 175, this);
        dailyWorkouts = addList("", 325, 150, 250, 175, this);
        healthReport = addList("You burn " + Context.getUser().getTargetCalories() + " calories at rest every day on average." + "|Net calories: " + netCalories + "|" + healthMessage, 50, 450, 525, 100, this);
        caloriesFromFood = addList("Total Calories Gained: " + adjudicator.calculateDailyCalories(fileHandler.getMealItems()), 50, 350, 250, 30, this);
        caloriesBurnedFromWorkout = addList("Total Calories Burned (workouts): " + adjudicator.calculateDailyCaloriesBurned(fileHandler.getWorkoutSessions()), 325, 350, 250, 30, this);

        dailyFoods.removeAll();
        dailyWorkouts.removeAll();

        fileHandler.printMealItems(dailyFoods);
        fileHandler.printWorkoutSessions(dailyWorkouts);

        setTitle("Diet Tracker");
        setSize(800, 600);
        title_diet.setFont(new Font("Arial", 0, 20));
        diet_date.setFont(new Font("Arial", 0, 20));
        title_diet_workout.setFont(new Font("Arial", 0, 20));
        title_diet_health.setFont(new Font("Arial", 0, 20));

        if (fromAddFood) {
            title_addFood.removeNotify();
            addFoodConfirm.removeNotify();
            foodBack.removeNotify();
            foodName.removeNotify();
            foodNameBox.removeNotify();
            calories.removeNotify();
            caloriesBox.removeNotify();
        } else if (fromAddWorkout) {
            title_addWorkout.removeNotify();
            addWorkoutConfirm.removeNotify();
            workoutBack.removeNotify();
            workoutName.removeNotify();
            workoutNameBox.removeNotify();
            caloriesBurned.removeNotify();
            caloriesBurnedBox.removeNotify();
        } else if (fromChooseFood) {
            title_chooseFood.removeNotify();
            chooseFoodConfirm.removeNotify();
            chooseFoodBack.removeNotify();
            choiceOfFood.removeNotify();
            meal.removeNotify();
            portionAmount.removeNotify();
            portionAmountBox.removeNotify();
        } else if (fromChooseWorkout) {
            title_chooseWorkout.removeNotify();
            chooseWorkoutConfirm.removeNotify();
            chooseWorkoutBack.removeNotify();
            choiceOfWorkout.removeNotify();
            workoutDuration.removeNotify();
            workoutDurationBox.removeNotify();
        } else if (fromCalendar) {
            fileHandler.createDateFile();
            title_calendar.removeNotify();
            weekdays.removeNotify();
            for (Button button : days) {
                button.removeNotify();
            }
            prevMonth.removeNotify();
            nextMonth.removeNotify();
        } else {
            title_login.removeNotify();
            login_confirm.removeNotify();
            login_username.removeNotify();
            usernameLoginBox.removeNotify();
            login_password.removeNotify();
            passwordLoginBox.removeNotify();
        }
        fromAddFood = false;
        fromAddWorkout = false;
        fromChooseFood = false;
        fromChooseWorkout = false;
        fromCalendar = false;
    }

    public void changeToAddFoodScreen() {
        title_addFood = addLabel("Add Food (per portion)", 50, 50, 300, 50, this);
        addFoodConfirm = addButton("Add", 300, 200, 60, 50, this);
        foodBack = addButton("Back", 50, 200, 60, 50, this);
        foodName = addLabel("Food Name: ", 50, 100, 100, 50, this);
        foodNameBox = addTextField("", 175, 100, 175, 40, this);
        calories = addLabel("Calories: ", 50, 150, 100, 50, this);
        caloriesBox = addTextField("", 175, 150, 175, 40, this);
        setTitle("Add Food");
        setSize(400, 300);
        title_addFood.setFont(new Font("Arial", 0, 20));
        foodNameBox.setFont(new Font("Arial", 0, 20));
        caloriesBox.setFont(new Font("Arial", 0, 20));
        title_diet.removeNotify();
        diet_date.removeNotify();
        calendarButton.removeNotify();
        addFood.removeNotify();
        addWorkoutItem.removeNotify();
        chooseFood.removeNotify();
        chooseWorkout.removeNotify();
        dailyFoods.removeNotify();
        title_diet_workout.removeNotify();
        dailyWorkouts.removeNotify();
        caloriesFromFood.removeNotify();
        caloriesBurnedFromWorkout.removeNotify();
        title_diet_health.removeNotify();
        healthReport.removeNotify();
    }

    public void changeToAddWorkoutItemScreen() {
        title_addWorkout = addLabel("Add Workout (per hour)", 50, 50, 300, 50, this);
        addWorkoutConfirm = addButton("Add", 300, 200, 60, 50, this);
        workoutBack = addButton("Back", 50, 200, 60, 50, this);
        workoutName = addLabel("Workout Name: ", 50, 100, 100, 50, this);
        workoutNameBox = addTextField("", 175, 100, 175, 40, this);
        caloriesBurned = addLabel("Calories Burned: ", 50, 150, 100, 50, this);
        caloriesBurnedBox = addTextField("", 175, 150, 175, 40, this);
        setTitle("Add Workout");
        setSize(400, 300);
        title_addWorkout.setFont(new Font("Arial", 0, 20));
        workoutNameBox.setFont(new Font("Arial", 0, 20));
        caloriesBurnedBox.setFont(new Font("Arial", 0, 20));
        title_diet.removeNotify();
        diet_date.removeNotify();
        calendarButton.removeNotify();
        addFood.removeNotify();
        addWorkoutItem.removeNotify();
        chooseFood.removeNotify();
        chooseWorkout.removeNotify();
        dailyFoods.removeNotify();
        title_diet_workout.removeNotify();
        dailyWorkouts.removeNotify();
        caloriesFromFood.removeNotify();
        caloriesBurnedFromWorkout.removeNotify();
        title_diet_health.removeNotify();
        healthReport.removeNotify();
    }

    public void changeToChooseFoodScreen() {
        foods = fileHandler.getFoodItems();
        foodNames = "";
        for (FoodItem f : foods) {
            foodNames += "|" + f.getName();
        }
        choiceOfFood = addChoice("Choose Food" + foodNames, 50, 100, 200, 50, this);
        meal = addChoice("Choose Meal|Breakfast|Lunch|Dinner|Snack", 50, 150, 200, 50, this);
        portionAmount = addLabel("How many portions?", 50, 180, 200, 50, this);
        portionAmountBox = addTextField("", 50, 230, 200, 30, this);
        title_chooseFood = addLabel("Choose Food", 50, 50, 200, 50, this);
        chooseFoodConfirm = addButton("Confirm", 300, 280, 60, 50, this);
        chooseFoodBack = addButton("Back", 50, 280, 60, 50, this);
        setTitle("Choose Food");
        setSize(400, 400);
        title_chooseFood.setFont(new Font("Arial", 0, 20));
        choiceOfFood.setFont(new Font("Arial", 0, 20));
        meal.setFont(new Font("Arial", 0, 20));
        portionAmount.setFont(new Font("Arial", 0, 20));
        portionAmountBox.setFont(new Font("Arial", 0, 20));
        title_diet.removeNotify();
        diet_date.removeNotify();
        calendarButton.removeNotify();
        addFood.removeNotify();
        addWorkoutItem.removeNotify();
        chooseFood.removeNotify();
        chooseWorkout.removeNotify();
        dailyFoods.removeNotify();
        title_diet_workout.removeNotify();
        dailyWorkouts.removeNotify();
        caloriesFromFood.removeNotify();
        caloriesBurnedFromWorkout.removeNotify();
        title_diet_health.removeNotify();
        healthReport.removeNotify();
    }

    public void changeToChooseWorkoutScreen() {
        workouts = fileHandler.getWorkoutItems();
        workoutNames = "";
        for (WorkoutItem w : workouts) {
            workoutNames += "|" + w.getName();
        }
        choiceOfWorkout = addChoice("Choose Workout" + workoutNames, 50, 100, 200, 50, this);
        workoutDuration = addLabel("Workout Duration:", 50, 130, 200, 50, this);
        workoutDurationBox = addTextField("", 50, 180, 200, 30, this);
        title_chooseWorkout = addLabel("Add Workout", 50, 50, 200, 50, this);
        chooseWorkoutConfirm = addButton("Add", 300, 230, 60, 50, this);
        chooseWorkoutBack = addButton("Back", 50, 230, 60, 50, this);
        setTitle("Choose Workout");
        setSize(400, 330);
        title_chooseWorkout.setFont(new Font("Arial", 0, 20));
        choiceOfWorkout.setFont(new Font("Arial", 0, 20));
        workoutDuration.setFont(new Font("Arial", 0, 20));
        workoutDurationBox.setFont(new Font("Arial", 0, 20));
        title_diet.removeNotify();
        diet_date.removeNotify();
        calendarButton.removeNotify();
        addFood.removeNotify();
        addWorkoutItem.removeNotify();
        chooseFood.removeNotify();
        chooseWorkout.removeNotify();
        dailyFoods.removeNotify();
        title_diet_workout.removeNotify();
        dailyWorkouts.removeNotify();
        caloriesFromFood.removeNotify();
        caloriesBurnedFromWorkout.removeNotify();
        title_diet_health.removeNotify();
        healthReport.removeNotify();
    }

}
