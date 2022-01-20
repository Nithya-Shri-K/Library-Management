package com.company;

import java.util.List;

public class Display {
    public static int displayMenuAndGetChoice(String displayMessage,int maxOptionValue) {

        int choice = 0, error = 1;
        Display.message(displayMessage);
        do {
            try {
                choice = Integer.parseInt(Main.read("Enter your Choice: "));
                Main.validateInputAndThrowException(choice, maxOptionValue);
                error = 0;
            } catch (NumberFormatException | InValidInputException e) {
                Display.message("Please enter a valid number from the Menu ");
                error = 1;
            }

        } while (error == 1);

        return choice;
    }

    public static void displayBooksDetails(List<Books> allBooks){
        int index=0;
        for (Books book:allBooks) {
            System.out.println(++index + ". Book Id: " + book.getBookId() + " Book Name: " + book.getBookName()+ " Quantity: " + book.getQuantityTotal() +
                    " Available: " + book.getQuantityAvailable() + " Borrowed: " + book.getQuantityBorrowed());
        }
    }
    public static void displayUserDetails(List<Users> allUsers){
        for (Users u:allUsers) {
            System.out.println("User Id: " + u.getUserId() + ", User Name: " + u.getName() + ", Borrowed books" + u.getBorrowedBooks());
        }

    }
    public static void displayHistory(List<String> history){
        int length= history.size();
        for (int i=length-1;i>=0;i--) {
            System.out.println(history.get(i));
        }
    }
    public static void message(String message){
        System.out.println(message);
    }

}
