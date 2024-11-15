package org.example.library;


import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<Book> borrowedBooks;

    public User(String name) {
        this.name = name;
        this.borrowedBooks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void borrowBook(Book book) {
        if (borrowedBooks.size() >= 5) {
            throw new IllegalStateException("You cannot borrow more than 5 books.");
        }
        book.borrow();
        borrowedBooks.add(book);
    }

    public void returnBook(Book book) {
        if (!borrowedBooks.contains(book)) {
            throw new IllegalArgumentException("This book was not borrowed by the user.");
        }
        book.returnBook();
        borrowedBooks.remove(book);
    }
}
