package org.example.Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BookEntry {

    private String title;
    private String author;
    private String genre;

    private int rating;

    //Set the current timestamp when the book gets created
    private LocalDateTime timestamp;
    private int numberOfBooks;

    //Field to add timestamp and to set the format o
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public BookEntry(String bookTitle, String author, String genre, int rating)
        {
            this.title = bookTitle;
            this.author = author;
            this.genre = genre;
            this.rating= rating;
            this.timestamp = LocalDateTime.now();

        }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    @Override
    public String toString() {
        return "BookEntry{" +
                "title='" + title + '\n' +
                ", author='" + author + '\n' +
                ", genre='" + genre + '\n' +
                ", rating=" + rating + '\n'+
                ", timestamp=" + timestamp + '\n'+
                '}'+ '\n';
    }
}



