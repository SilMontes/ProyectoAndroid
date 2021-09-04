package com.silmontes.proyectoandroid;

import java.time.LocalDate;
import java.util.ArrayList;

public class DataManager {

    private static DataManager dataManagerInstance;
    private static ArrayList<Book> allBooks;
    private static ArrayList<Book> currentlyReadingBooks;
    private static ArrayList<Book> alreadyReadBooks;
    private static ArrayList<Book> wantToReadBooks;
    private static ArrayList<Book> favoriteBooks;

    public DataManager() {
        if(null == allBooks){
            allBooks = new ArrayList<>();
            initAllBooksData();
        }

        if(null == currentlyReadingBooks){
            currentlyReadingBooks = new ArrayList<>();
        }
        if(null == alreadyReadBooks){
            alreadyReadBooks = new ArrayList<>();
        }
        if(null == allBooks){
            allBooks = new ArrayList<>();
        }
        if(null == wantToReadBooks){
            wantToReadBooks = new ArrayList<>();
        }
        if(null == favoriteBooks){
            favoriteBooks = new ArrayList<>();
        }
    }

    private void initAllBooksData() {

        allBooks.add(new Book(1,
                "1Q84",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
        allBooks.add(new Book(2,
                "1111",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
        allBooks.add(new Book(3,
                "2222",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
        allBooks.add(new Book(4,
                "333",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
        allBooks.add(new Book(5,
                "444",
                "dANI LOU",
                130,
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQIju3DElwC8G9wX_5OXhQjUA9SlRlZjyjJ1Q&usqp=CAU.jpn",
                "A work of madening brilliance",
                "Long description",
                LocalDate.of(2000,12,30)));
    }

    public Book getBookById(int id){
        for(Book book:allBooks){
            if(book.getId() == id){
                return book;
            }
        }
        return null; // If it was not found we return null
    }

    public boolean addToAlreadyRead(Book book){
        return alreadyReadBooks.add(book); // if it returns true, the object was successfully added
    }
    public boolean addToWantRead(Book book){
        return wantToReadBooks.add(book); // if it returns true, the object was successfully added
    }
    public boolean addToFavoriteBooks(Book book){
        return favoriteBooks.add(book); // if it returns true, the object was successfully added
    }
    public boolean addToCurrentlyReading(Book book){
        return currentlyReadingBooks.add(book); // if it returns true, the object was successfully added
    }
    public static ArrayList<Book> getAllBooks() {
        return allBooks;
    }

    public static ArrayList<Book> getCurrentlyReadingBooks() {
        return currentlyReadingBooks;
    }

    public static ArrayList<Book> getAlreadyReadBooks() {
        return alreadyReadBooks;
    }

    public static ArrayList<Book> getWantToReadBooks() {
        return wantToReadBooks;
    }

    public static ArrayList<Book> getFavoriteBooks() {
        return favoriteBooks;
    }

    public static  DataManager getInstance(){
        if(null == dataManagerInstance){
            dataManagerInstance = new DataManager();
            return dataManagerInstance;
        }else{
            return dataManagerInstance;
        }
    }
}
