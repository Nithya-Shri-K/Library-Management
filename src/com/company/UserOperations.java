package com.company;

import java.util.ArrayList;
import java.util.List;

public class UserOperations {
    public void addDefaultUsers(){
        Users user1 = new Users("Nithya");
        LibraryDb.getUsers().add(user1);
        Users user2 = new Users("Rasal");
        LibraryDb.getUsers().add(user2);
        Users user3 = new Users("Neya");
        LibraryDb.getUsers().add(user3);
        Users user4 = new Users("Rosy");
        LibraryDb.getUsers().add(user4);
        Users user5 = new Users("Kalimuthu");
        LibraryDb.getUsers().add(user5);
    }

    public Users createUser(String name){
        Users u = new Users(name);
        LibraryDb.getUsers().add(u);
        return u;
    }
    public Users getUser(){
        int userType,userId;
        String userName;
        Users currentUser =null;
        userType=Integer.parseInt(Main.read("already a User ? if yes enter '1' else enter '0' "));
        if(userType==0) {
            userName=Main.read("User Name: ");
            currentUser = getCurrentUser(userName);
        }
        else{
            userId=Integer.parseInt(Main.read("User Id: "));
            currentUser= getCurrentUser(userId);
        }
        return currentUser;
    }

    public Users getCurrentUser(String userName) {
        Users currentUser = createUser(userName);
        return currentUser;
    }
    public Users getCurrentUser(int userId) {
        Users currentUser=null;
        for (Users u:LibraryDb.getUsers()) {
            if(userId==u.getUserId())
                currentUser=u;
        }
        return currentUser;
    }
    public List<Users> showUsers() {
        return LibraryDb.getUsers();
    }
    public List<Users> showUsers(int userId) {
        List<Users> user = new ArrayList<>();
        for (Users u:LibraryDb.getUsers()) {
            if(u.getUserId()==userId) {
                user.add(u);
                break;
            }
        }
        return user;
    }
    public List<String> userHistory(int userId){
        return LibraryDb.getHistory().get(userId);
    }
}
