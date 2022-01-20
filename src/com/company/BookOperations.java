package com.company;

import java.util.ArrayList;
import java.util.List;

public class BookOperations {
    public void addDefaultBooks() {
        Books book1 = new Books("Secret of Success",5,3,2);
        LibraryDb.getAllBook().add(book1);
        Books book2 = new Books("The alchemist",7,4,3);
        LibraryDb.getAllBook().add(book2);
        Books book3 = new Books("Harry Potter",6,6,0);
        LibraryDb.getAllBook().add(book3);
        Books book4 = new Books("The Art of Thinking Clearly",3,2,1);
        LibraryDb.getAllBook().add(book4);
        Books book5 = new Books("Little Book of Happiness",9,4,5);
        LibraryDb.getAllBook().add(book5);
    }
    public Books createBookRecord(String bookName,int quantityTotal) {
        Books book = new Books(bookName,quantityTotal);
        return book;
    }
    public List<Books> bookStatus() {
        return LibraryDb.getAllBook();
    }
    public List<Books> searchByName(String searchBookName) {
        List<Books> searchResult= new ArrayList<>();
        for (Books book:LibraryDb.getAllBook()) {
            if(book.getBookName().toLowerCase().startsWith(searchBookName.toLowerCase()))
                searchResult.add(book);
        }
        return searchResult;
    }

    public int borrowABook(Users currentUser, Books borrowBook){

        int available,borrowed,bookBorrowedStatus=0;
        if(borrowBook.getQuantityAvailable()>0){
            available=borrowBook.getQuantityAvailable()-1;
            borrowBook.setQuantityAvailable(available);
            borrowed=borrowBook.getQuantityBorrowed()+1;
            borrowBook.setQuantityBorrowed(borrowed);
            currentUser.getBorrowedBooks().add(borrowBook.getBookName());
            currentUser.getUserHistory().add("Borrowed - " + borrowBook.getBookName());
            bookBorrowedStatus=1;
        }
        return bookBorrowedStatus;
    }

    public int returnABook(Users currentUser, String bookName) {
        int isBorrowed=0;
        Books returnBook=null;
        for (Books book:LibraryDb.getAllBook()) {
            if(book.getBookName().toLowerCase().equals(bookName.toLowerCase())) {
                returnBook=book;
                break;
            }
        }
        for (String book:currentUser.getBorrowedBooks()) {
            if(book.compareTo(returnBook.getBookName())==0) {
                isBorrowed=1;
                break;
            }
        }
        if(isBorrowed==1) {
            currentUser.getBorrowedBooks().remove(returnBook.getBookName());
            currentUser.getUserHistory().add("returned - " + returnBook.getBookName());
            returnBook.setQuantityAvailable(returnBook.getQuantityAvailable() + 1);
            returnBook.setQuantityBorrowed(returnBook.getQuantityBorrowed() - 1);
        }
        return isBorrowed;
    }
    public void editBookName(Books book,String newBookName){
        book.setBookName(newBookName);
    }
    public void editQuantity(Books book,int newBookQuantity){
        book.setQuantityTotal(newBookQuantity);
        book.setQuantityAvailable(newBookQuantity-book.getQuantityBorrowed());
    }
    public void removeBook(Books book) {
        LibraryDb.getAllBook().remove(book);
    }

}
