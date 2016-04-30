package com.github.tonydeng.demo.java8.lambda;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by tonydeng on 16/4/28.
 */
public class RoboContactLambda {

    public void phoneContacts(List<Person> pl, Predicate<Person> pred) {
        for (Person p : pl) {
            if (pred.test(p)) {
                roboCall(p);
            }
        }
    }

    public void emailContacts(List<Person> pl, Predicate<Person> pred){
        for (Person p : pl) {
            if (pred.test(p)) {
                roboEmail(p);
            }
        }
    }

    public void roboCall(Person p) {
        System.out.println("Calling p = [" + p + "]");
    }

    public void roboEmail(Person p) {
        System.out.println("Emailing p = [" + p + "]");
    }

    public static void main(String[] args){
        List<Person> pl = Person.createShortList();

        RoboContactLambda robo = new RoboContactLambda();

        Predicate<Person> allDirvers = p -> p.getAge() >= 16;

        Predicate<Person> allDraftees = p -> p.getAge() >=18 &&
                p.getAge() <= 25 && p.getGender() == Person.Gender.MALE;

        System.out.println("Calling all Drivers");
        robo.phoneContacts(pl,allDirvers);

        System.out.println("Email all Draftees");
        robo.emailContacts(pl,allDraftees);
    }

}
