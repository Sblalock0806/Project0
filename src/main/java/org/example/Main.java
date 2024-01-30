package org.example;
import org.example.Exception.BookException;
import org.example.Exception.MainException;
import org.example.Model.BookEntry;
import org.example.Service.BookService;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//File for Demo
// C:\Users\U43SGB\OneDrive - Government Employees Insurance Company\Documents\project_0_test_lists.csv
//exception case -- "C:\Users\U43SGB\OneDrive - Government Employees Insurance Company\Documents\project_0_test_lists_exception_case.csv"
public class Main {
    public static Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws FileNotFoundException, BookException, MainException {
        System.out.println("Hello and Welcome to my Reading tracker app! You will be able to add, search, view all books, import an list from a .csv file, export your list to .csv file and delete books.");

/// Instantiating the use of BookService into Main
/// Lowercase bookService is the new variable to use BookService
        BookService bookService = new BookService();
        Scanner sc = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("Please type what you would like to do: " + '\n' +
                    "Add(A) " + '\n' +
                    "View(V)" + '\n' +
                    "Search (S)" + '\n' +
                    "Import a .csv file with your book list(I)" + '\n' +
                    "Export a .csv file of your book list(E)" + '\n' +
                    "Delete (D)" + '\n'+
                    "Exit Program (press any other key)" );

            String userInput = sc.nextLine().toUpperCase();

//Add Book method
            if (userInput.equals("ADD") || userInput.equals("A")) {
                try {
                    System.out.println("Enter the number of books you would like to add: ");
                    int numberOfBooks = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < numberOfBooks; i++) {
                        Main.log.info("Attempting to add # number of books: " + numberOfBooks);
                        System.out.println("Enter details for Book # " + (i + 1));
                        System.out.println("Please input the Book Title: ");
                        String titleInput = sc.nextLine();
                        System.out.println("Please Input the First and Last Name of the Author");
                        String authorInput = sc.nextLine();
                        System.out.println("Please Input the genre (Horror, Mystery, Thriller, Science Fiction, Romance, Action, Classic)");
                        String genreInput = sc.nextLine();
                        System.out.println("Please Input a Rating (1 through 5)");
                        String ratingInput = sc.nextLine();
                        bookService.addBook(titleInput, authorInput, genreInput, Integer.parseInt(ratingInput));
                        System.out.println("Book added Successfully!");
                    }
                } catch (InputMismatchException e) {
                    Main.log.warn("InputMismatchException generated.");
                    System.out.println("Invalid input. Please enter a valid number. ");
                    sc.nextLine();
                } catch (Exception e) {
                    Main.log.warn("Exception generated.");
                    System.out.println(" An unexpected error has occurred: " + e.getMessage());
                }
            }

// View Method
            else if (userInput.equals("VIEW") || userInput.equals("V")) {
                Main.log.info("viewing list");
                System.out.println("You are viewing all book entries at this point: ");
                List<BookEntry> entries = bookService.getBookList();
                System.out.println(entries);
            }

// Search Method
            else if (userInput.equals("SEARCH") || userInput.equals("S")) {
                Main.log.info("searching list");
                System.out.println("Please enter the search criteria  ( title (t) / author (a)/ genre (g)/ rating (r):");
                String criteria = sc.nextLine();
                Main.log.info("searching list by " + criteria);
                System.out.println("What " + criteria + " are you searching for:  ");
                String value = sc.nextLine();
                Main.log.info("searching list by " + criteria + " and '" + value +"'.");
                bookService.searchBooks(criteria, value);
            }

// Delete Method
            else if (userInput.equals("DELETE") || userInput.equals("D")) {
                Main.log.info("request to delete a book by title");
                Main.log.info("test test to delete a book by title");
                System.out.println("Enter the full book title you would like to delete:");
                String deleteTitle = sc.nextLine();
                Main.log.info("deleting " + deleteTitle);
                bookService.deleteBook(deleteTitle);
            }

// Import .csv file
            else if (userInput.equals("IMPORT") || userInput.equals("I")) {
                Main.log.info("request to import csv file");
                System.out.println("Please enter the file path of the csv file you would like to import:");
                String importList = sc.nextLine();
                bookService.loadInitialBooksFromCSV(importList);
                Main.log.info("csv file imported");
            }
// Export to .csv file
            else if (userInput.equals("EXPORT") || userInput.equals("E")) {
                Main.log.info("request to export csv file");
                System.out.println("Please enter the file path for the csv file you would like to export:");
                System.out.println("example: C:\\Users\\U43SGB\\Documents\\testfile.csv");
                String exportFilePath = sc.nextLine();
                bookService.exportToCSV(exportFilePath);
                Main.log.info("csv file exported");
            }

            else {
                Main.log.info("Invalid input entered." + userInput);
                throw new MainException("not a valid command. Please rerun the program");


            }
        }
    }
}

