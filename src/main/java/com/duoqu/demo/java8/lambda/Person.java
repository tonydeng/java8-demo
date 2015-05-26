package com.duoqu.demo.java8.lambda;

/**
 * Created by tonydeng on 15/5/26.
 */
public class Person {
    String firstName;
    String lastName;

    Person(){}

    Person(String firstName,String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
