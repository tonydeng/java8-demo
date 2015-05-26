package com.duoqu.demo.java8.lambda;

/**
 * Created by tonydeng on 15/5/26.
 */
public interface PersonFactory <P extends Person >{
    P create(String firstName,String lastName);
}
