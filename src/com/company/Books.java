package com.company;

public class Books {
    private int bookId;
    private String bookName;
    private int quantityTotal;
    private  int quantityAvailable;
    private  int quantityBorrowed;
    static int idCreator=0;
    Books(String bookName,int quantityTotal)
    {
        this.bookId=++idCreator;
        this.bookName=bookName;
        this.quantityTotal=quantityTotal;
        this.quantityAvailable=quantityTotal;
        this.quantityBorrowed=0;
    }
    Books(String bookName,int quantityTotal,int quantityAvailable,int quantityBorrowed)
    {
        this.bookId=++idCreator;
        this.bookName=bookName;
        this.quantityTotal=quantityTotal;
        this.quantityAvailable=quantityAvailable;
        this.quantityBorrowed=quantityBorrowed;

    }

    public int getBookId() {
        return bookId;
    }
    public String getBookName() {
        return bookName;
    }
    public int getQuantityTotal() {
        return quantityTotal;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public int getQuantityBorrowed() {
        return quantityBorrowed;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setQuantityBorrowed(int quantityBorrowed) {
        this.quantityBorrowed = quantityBorrowed;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setQuantityTotal(int quantityTotal) {
        this.quantityTotal = quantityTotal;
    }

}

