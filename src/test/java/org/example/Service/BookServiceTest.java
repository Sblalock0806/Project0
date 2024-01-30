package org.example.Service;

import com.sun.tools.attach.AgentInitializationException;
import org.example.Exception.BookException;
import org.example.Service.BookService;
import junit.framework.TestCase;
import org.example.Model.BookEntry;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;
import java.util.List;

import java.io.File;
import java.net.URL;

import static org.junit.Assert.*;

public class BookServiceTest {

    BookService bookService;


    @Before
    public void setup() {
        bookService = new BookService();
    }

    //Blank List Test
    @Test
    public void recipeServiceEmptyAtStart() {
        List<BookEntry> bookList = bookService.getBookList();
//        ensure that at the start, there are no books
        Assert.assertTrue(bookList.size() == 0);
    }

    /*
     * The getClass.etc retrieves the test csv file from the resources' folder.  It then uses the absolute path of the file to pass the loadInitialBooksFromCSV method.
     * An absolute path makes no assumption about your current location in relation to the location of the file
     * obtained from internet
     */

    //Import CSV file Test
    @Test
    public void testLoadInitialBooksFromCSV() throws BookException {
        //get file path of test csv file created in resources folder
        URL resourceUr1 = getClass().getClassLoader().getResource("testBooks.csv");
        if (resourceUr1 == null) {
            throw new RuntimeException("Test CSV file not found.");
        }
        String testFilePath = new File(resourceUr1.getFile()).getAbsolutePath();

        bookService.loadInitialBooksFromCSV((testFilePath));
    }

    //Test Add Book
    @Test
    public void testAddBook() throws BookException {
        bookService.addBook("testBook","testAuthor","testGenre",4);
        assertEquals(1, bookService.getBookList().size());
    }



    //Search Book Test
    @Test
       public void testSearchBooks() throws BookException {
        bookService.addBook("testBook1", "testAuthor1", "testGenre1", 1);
        bookService.addBook("testBook2", "testAuthor2", "testGenre2", 2);
        bookService.addBook("NegativeTest", "testAuthor3", "testGenre3", 3);

        List<String> searchResults = bookService.searchBooks("title", "testBook1");

        assertEquals(1, searchResults.size());

    }

    //test search for no book found
    @Test
    public void testMissingSearchBooks() throws BookException {
        bookService.addBook("testBook1", "testAuthor1", "testGenre1", 1);
        bookService.addBook("testBook2", "testAuthor2", "testGenre2", 2);
        bookService.addBook("NegativeTest", "testAuthor3", "testGenre3", 3);

        List<String> searchResults = bookService.searchBooks("title", "testBook5");

        assertEquals(0, searchResults.size());

    }
    // Delete Book Test
       @Test
       public void testDeleteBook() throws BookException {
        bookService.addBook("test Book1","test Author1","Genre1",4);
       bookService.addBook("test Book2","test Author2","Genre2",3);

       bookService.deleteBook("test Book1");

       assertEquals(1,bookService.getBookList().size());
       assertEquals("test Book2", bookService.getBookList().get(0).getTitle());
}

    @Test
    public void testMissingDeleteBook() throws BookException {
        bookService.addBook("test Book1","test Author1","Genre1",4);
        bookService.addBook("test Book2","test Author2","Genre2",3);

        bookService.deleteBook("test Book4");

        assertEquals(2,bookService.getBookList().size());
    }
}


