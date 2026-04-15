package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class AppTest {

    @Test
    public void testInheritance() {
        Dog myDog = new Dog();
        
        // Test inherited method from Animal
        assertEquals("This animal eats food.", myDog.eat());
        
        // Test Dog's own method
        assertEquals("Woof!", myDog.bark());
    }

    @Test
    public void testType() {
        Dog myDog = new Dog();
        // Verify the "is-a" relationship
        assertTrue(myDog instanceof Animal, "Dog should be an instance of Animal");
    }
}