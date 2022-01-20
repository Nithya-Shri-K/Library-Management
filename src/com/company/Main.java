package com.company;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class Main {

    private static Scanner sc = new Scanner(System.in);
    private static Library librarySystem = new Library();
    private static BookOperations executeBookOperation= new BookOperations();
    private static UserOperations executeUserOperation = new UserOperations();

    public static void main(String[] args) {
        executeBookOperation.addDefaultBooks();
        executeUserOperation.addDefaultUsers();
        mainModule();

    }

    public static void mainModule() {
        char displayMainMenu = 'y';
        do {
            int choice = Display.displayMenuAndGetChoice("Library Management System\n 1. System\n 2. User\n 3.Exit", 3);
            switch (choice) {
                case 1:
                    systemModule();
                    break;
                case 2:
                    userModule();
                    break;
                case 3:
                    displayMainMenu = 'n';
                    break;
            }

        } while (displayMainMenu == 'y');

    }

    public static void systemModule() {
        int displaySystemMenu = 'y';
        String searchBookName;
        String bookName;
        int quantityTotal,userId;
        char isEdit;
        do {
            int menuChoice= Display.displayMenuAndGetChoice("Welcome\n 1. Add a Book\n 2. Book status\n 3. Search a Book\n 4.Remove a book\n 5.Users \n 6.User History\n 7.Exit\n",7);
            switch (menuChoice) {
                case 1:
                    bookName=Main.read("Book Name: ");
                    quantityTotal=Integer.parseInt(read("Quantity: "));
                    Books book=executeBookOperation.createBookRecord(bookName,quantityTotal);
                    LibraryDb.getAllBook().add(book);
                    break;
                case 2:
                    Display.displayBooksDetails(executeBookOperation.bookStatus());
                    break;
                case 3:
                    List<Books> searchResult;
                    int indexNo;
                    searchBookName = read("Book Name: ");
                    searchResult= executeBookOperation.searchByName(searchBookName);
                    if(!searchResult.isEmpty()) {
                        Display.displayBooksDetails(searchResult);
                        isEdit = read("Do you want to edit a book? (y/n)").charAt(0);
                        if (isEdit == 'y') {
                            indexNo = Display.displayMenuAndGetChoice("Select the book to edit ",searchResult.size());
                            Main.editModule(searchResult.get(indexNo - 1));
                        }
                    }
                    else
                    {
                        Display.message("No books found");
                    }
                    break;
                case 4:
                    bookName=Main.read("Book Name: ");
                    searchResult= executeBookOperation.searchByName(bookName);
                    Display.displayBooksDetails(searchResult);
                    indexNo = Display.displayMenuAndGetChoice("Select a book to remove ",searchResult.size());
                    executeBookOperation.removeBook(searchResult.get(indexNo-1));
                    Display.message("book removed!");
                    break;
                case 5:
                    Display.displayUserDetails(executeUserOperation.showUsers());
                    break;
                case 6:
                    userId = Integer.parseInt(Main.read("User Id: "));
                    List<Users> user=executeUserOperation.showUsers(userId);
                    if(user.isEmpty())
                        System.out.println("User not found");
                    else {
                        Display.displayUserDetails(user);
                        Display.displayHistory(executeUserOperation.userHistory(userId));
                    }
                    break;
                case 7:
                    displaySystemMenu = 'n';
                    break;
            }
        }while (displaySystemMenu == 'y');
    }
    public static void userModule(){
        int displayUserMenu='y',indexNo,error=1;
        String bookNameSearch,bookName;
        char isBorrow;
        Users currentUser= executeUserOperation.getUser();
        List<Books> searchResult;
        System.out.println("Hey " + currentUser.getName() + "!!");
        while (displayUserMenu == 'y') {
            int menuChoice= Display.displayMenuAndGetChoice("Welcome\n 1. Search and borrowABook \n 2. Return a Book\n 3. My Profile\n 4. Exit\n",5);
            switch (menuChoice) {
                case 1:
                    bookNameSearch = Main.read("Book Name: ");
                    searchResult = executeBookOperation.searchByName(bookNameSearch);
                    if (!searchResult.isEmpty()) {
                        Display.displayBooksDetails(searchResult);
                        isBorrow=Main.read("Do you want to borrowABook books? (y/n)").charAt(0);
                        if(isBorrow=='y'){
                            indexNo =Display.displayMenuAndGetChoice("select a book to borrowABook", searchResult.size());
                            executeBookOperation.borrowABook(currentUser, searchResult.get(indexNo - 1));
                            Display.message("book borrowed successfully");
                        }
                    }
                    else{
                        Display.message("no books found");
                    }
                    break;
                case 2:
                    bookName=read("Book Name: ");
                    if(executeBookOperation.returnABook(currentUser,bookName)==0)
                        Display.message("you did not Borrow the book \"" + bookName + "\"");
                    else
                        Display.message("Book Returned successfully");
                    break;
                case 3:
                    Display.displayUserDetails(executeUserOperation.showUsers(currentUser.getUserId()));
                    Display.displayHistory(executeUserOperation.userHistory(currentUser.getUserId()));
                    break;
                case 4: displayUserMenu='n';
                    break;
            }
        }
    }
    public static void editModule(Books editBook){
        int newBookQuantity;
        char displayEditMenu='y';
        do {
            int menuChoice= Display.displayMenuAndGetChoice("1. Edit Book Name\n2. Edit Quantity\n3. Exit",3);
            switch (menuChoice) {
                case 1:
                    String newBookName = Main.read("New Book Name: ");
                    executeBookOperation.editBookName(editBook,newBookName);
                    Display.message("Book Name Changed successfully");
                    break;
                case 2:
                    newBookQuantity = Integer.parseInt(Main.read("New Book Quantity: "));
                    executeBookOperation.editQuantity(editBook,newBookQuantity);
                    Display.message("Book Quantity Changed successfully");
                    break;
                case 3:
                    displayEditMenu = 'n';
                    break;
            }
        } while (displayEditMenu == 'y');
    }
    public static void validateInputAndThrowException(int input, int maxValue) throws InValidInputException{
        if(input<=0 || input>maxValue){
            throw new InValidInputException();
        }
    }
    public static String read(String prompt){
        String value;
        Display.message(prompt);
        value=sc.nextLine();
        return value;
    }


}
