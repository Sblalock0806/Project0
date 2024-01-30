package org.example.Service;

import org.example.Exception.BookException;
import org.example.Main;
import org.example.Model.BookEntry;

import java.awt.print.Book;
import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static java.lang.String.valueOf;

public class BookService {
    List<BookEntry> bookList;

    public BookService() {
        this.bookList = new ArrayList<>();
    }

    /*
     ** Add a book to booklist Method **
     *  Exceptions are thrown if rating is less than 1 or greater than 5, title is left blank,
     *       author left blank, or genre left blank
     * Logger included to track details
     */
    public void addBook(String title, String author, String genre, int rating) throws BookException {
        {
            Main.log.info("Attempting to add a book." + title + ", " + author + ", " + genre + ", " + rating);
            if (rating < 1 || rating > 5) {
                Main.log.warn("Throwing book exception due to miss-formatted rating: " + rating);
                throw new BookException("Rating must be between 1 and 5");
            } else if (title.length() < 1) {
                Main.log.warn("throwing Book exception due to miss-formatted title: " + title);
                throw new BookException("Title is blank");
            } else if (author.length() < 1) {
                Main.log.warn("throwing book exception due to miss-formatted author: " + author);
                throw new BookException("Author is blank");
            } else if (genre.length() < 1) {
                Main.log.warn("throwing book exception due to miss-formatted genre: " + genre);
                throw new BookException("Genre is blank");
            }
            BookEntry book = new BookEntry(title, author, genre, rating);
            bookList.add(book);
        }
    }

    /*
     ** Get Book List Method **
     * Return all books from the array List sorted by book title using comparator.
     */
    public List<BookEntry> getBookList() {
        Main.log.info("Viewed sorted list of books");
        bookList.sort(Comparator.comparing(BookEntry::getTitle, String.CASE_INSENSITIVE_ORDER));
        return this.bookList;
    }

    /*
     ** Search Book Method**
     *This method allows users to search by a given criteria (title,author, genre, rating) and value
     */
    public List<String> searchBooks(String searchCriteria, String searchValue) {

// create a new list to store the search results
        List<String> searchResults = new ArrayList<>();

//convert the search value to lower case for case-insensitive match
        String searchValueLowerCase = searchValue.toLowerCase();

// Iterate through each book in the book list
        for (BookEntry book : bookList) {

            boolean match = false;

//Convert the book attribute(differs depending the criteria) to lowercase for case-insensitive matching
            String attributeLowerCase;

// Check the search criteria and set the attribute to lower case
            if ("title".equalsIgnoreCase(searchCriteria) || "t".equalsIgnoreCase(searchCriteria)) {
                attributeLowerCase = book.getTitle().toLowerCase();
            } else if ("author".equalsIgnoreCase(searchCriteria) || "a".equalsIgnoreCase(searchCriteria)) {
                attributeLowerCase = book.getAuthor().toLowerCase();
            } else if ("genre".equalsIgnoreCase(searchCriteria) || "g".equalsIgnoreCase(searchCriteria)) {
                attributeLowerCase = book.getGenre().toLowerCase();
            } else if ("rating".equalsIgnoreCase(searchCriteria) || "r".equalsIgnoreCase(searchCriteria)) {
                attributeLowerCase = valueOf(book.getRating()).toLowerCase();
            } else {
                Main.log.info("Search criteria used is not valid");
                System.out.println("Invalid Search Criteria.");
                return searchResults;
            }
// Check if the lowercase search value is contained within the lowercase attribute
            if (attributeLowerCase.contains(searchValueLowerCase)) {
                Main.log.info("Searching to see if keyword is in searchResults and if available will add to searchResults");
                searchResults.add(book.toString());
            }
        }
        if (searchResults.isEmpty()) {
            Main.log.info("Searching using keyword did not find any results" + searchValueLowerCase);
            System.out.println("No books found based on the search criteria.");
        } else {
            Main.log.info("Pulling search results using keyword, " + searchValueLowerCase);
            System.out.println("Search results:");
            for (String book : searchResults) {
                System.out.println(book);
            }
        }
        return searchResults;
    }


    // This method deletes the book by book title. This requires the user to input the full book title however it ignore case
    public void deleteBook(String bookTitle) {
        boolean remove = bookList.removeIf((book -> book.getTitle().equalsIgnoreCase(bookTitle)));

        if (remove) {
            Main.log.info(bookTitle + " is being removed from bookList");
            System.out.println("Book deleted successfully.");
        } else {
            Main.log.info(bookTitle + " is not found within bookList therefore it is not being deleted");
            System.out.println("Book not found in the reading list.");
        }
    }

    // Method to get the initial list of books from a csv file
    public void loadInitialBooksFromCSV(String filePath) throws BookException {
        try (BufferedReader reader = new BufferedReader((new FileReader(filePath)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) {
                    addBook(parts[0].trim(), parts[1].trim(), parts[2].trim(), Integer.parseInt(parts[3].trim()));
                } else {
                    Main.log.info("Invalid row in csv file," + line);
                    System.out.println(" Invalid CSV Line: " + line);
                }
            }
            System.out.println(" Initial books loaded successfully from CSV.");
        } catch (IOException e) {
            Main.log.error("IOException created due to invalid csv file");
            System.out.println(" Error reading csv file: " + e.getMessage());
        }
    }

//        public void exportToCSV (String filePath){
//            try {
//                FileWriter csvWriter = new FileWriter(filePath);
//                csvWriter.append("Title,Author,Genre,Rating\n");
//
//                for (BookEntry book : bookList) {
//                    csvWriter.write(book.getTitle() + ",");
//                    csvWriter.write(book.getAuthor() + ",");
//                    csvWriter.write(book.getGenre() + ",");
//                    csvWriter.write(String.valueOf(book.getRating()));
//                    csvWriter.write("\n");
//                }
//
//                csvWriter.flush();
//                csvWriter.close();
//                System.out.println("CSV File created successfully.");
//            }catch (IOException e) {
//                System.out.println("An error occured while creating the CSV file." + e.getMessage());
//            }
//    }

    public void exportToCSV (String filePath){
        try {
            FileWriter csvWriter = new FileWriter(filePath);
            File creatfile = new File(filePath);
            csvWriter.append("Title,Author,Genre,Rating\n");

            for (BookEntry book : bookList) {
                csvWriter.write(book.getTitle() + ",");
                csvWriter.write(book.getAuthor() + ",");
                csvWriter.write(book.getGenre() + ",");
                csvWriter.write(String.valueOf(book.getRating()));
                csvWriter.write("\n");
            }
            creatfile.createNewFile();
            csvWriter.flush();
            csvWriter.close();
            System.out.println("CSV File created successfully.");
        }catch (IOException e) {
            System.out.println("An error occurred while creating the CSV file." + e.getMessage());
        }
    }

        }


