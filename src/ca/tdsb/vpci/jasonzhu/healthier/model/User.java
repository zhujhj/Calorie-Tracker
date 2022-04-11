package ca.tdsb.vpci.jasonzhu.healthier.model;

public class User {
    private double age;
    private double weight;
    private double height;
    private String gender;
    private String username;
    private String password;
    private String confirmPassword;
    private double targetCalories;

    public void setAge(double age) {
        this.age = age;
    }

    public double getAge() {
        return this.age;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getWeight() {
        return this.weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getHeight() {
        return this.height;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return this.gender;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return this.password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public double getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(double targetCalories) {
        this.targetCalories = targetCalories;
    }

    public String toString() {
        return username + ", " + password + ", " + confirmPassword + ", " + age
                + ", " + weight + ", " + height + ", " + gender + ", " + targetCalories;
    }

}
