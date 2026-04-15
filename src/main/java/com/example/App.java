package com.example;

// Superclass
class Animal {
    String name;
    
    public String eat() {
        return "This animal eats food.";
    }
}

// Subclass
class Dog extends Animal {
    public String bark() {
        return "Woof!";
    }
}

public class App {
    // This method makes it easy for your JUnit test to call the logic
    public static String getDogBark() {
        Dog myDog = new Dog();
        return myDog.bark();
    }

    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.name = "Buddy";
        System.out.println(myDog.name + " says: " + myDog.bark());
    }
}