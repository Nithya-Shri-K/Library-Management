package com.company;
import java.util.*;
public class Users {
    private static int counter=0;
    private int userId;
    private String name;
    private List<String> borrowedBooks = new ArrayList<>();
    private List<String> userHistory = new LinkedList<>();

    Users(String name)
    {
        this.userId=++counter;
        this.name=name;
        LibraryDb.getHistory().put(userId, userHistory);
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public List<String> getBorrowedBooks() {
        return borrowedBooks;
    }
    public List<String> getUserHistory() {
        return userHistory;
    }
}
