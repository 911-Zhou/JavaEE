package org.example.springwebdemo119.pojo;

public class user {
    private String Name;
    private int Age;
    private int  gender;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "user{" +
                "Name='" + Name + '\'' +
                ", Age=" + Age +
                ", gender=" + gender +
                '}';
    }
}
