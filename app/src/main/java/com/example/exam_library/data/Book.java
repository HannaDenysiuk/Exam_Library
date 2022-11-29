package com.example.exam_library.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Book implements Serializable {
    private String author;
    private String title;
    private String genre;
    private String publishingHouse;
    private int publishingYear;
    private int pages;

    public Book() {
    }

    public Book(String author, String title, String genre, String publishingHouse,
                int year, int pages) {
        this.author = author;
        this.title = title;
        this.genre = genre;
        this.publishingHouse = publishingHouse;
        this.publishingYear = year;
        this.pages = pages;
    }
    public static List<Book> defaultCollection(){
        List<Book> books = new ArrayList<>();
        books.add(new Book("Benjamin Franklin", "The Autobiography of Benjamin Franklin",
                "biography", "Createspace Independent Publishing Platform",
                1996,192));
        books.add(new Book("Charlotte Brontë", "Jane Eyre",
                "	Gothic Bildungsroman Romance","Penguin Group",
                2006, 624));
        books.add(new Book(" Dale Carnegie","How to Win Friends & Influence People",
                "Self-help","Pocket Books",1998, 320));
        books.add(new Book("Dale Carnegie, Dorothy Carnegie, Meltem Erkmen Dale Carnegie",
                "Self-help","How to Stop Worrying and Start Living","Gallery Books",
                2004,298));
        return books;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public int getYear() {
        return publishingYear;
    }

    public int getPages() {
        return pages;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public void setYear(int year) {
        this.publishingYear = year;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }
}
