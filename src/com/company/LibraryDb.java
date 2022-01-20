package com.company;
import java.io.IOException;
import java.io.File;
import java.util.*;


public class LibraryDb {
    private static List<Books> allBooks = new ArrayList<>();
    private static List<Users> allUsers = new ArrayList<>();
    private static Map<Integer, List<String>> history = new HashMap<>();
    public static List<Books> getAllBook(){
        return allBooks;
    }
    public static List<Users> getUsers(){
        return allUsers;
    }
    public static Map<Integer, List<String>> getHistory(){
        return history;
    }


}
