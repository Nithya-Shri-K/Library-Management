package com.company;

public class InValidInputException extends RuntimeException{
    InValidInputException(){
        super("Enter a Valid Choice from the Menu");
    }
}

