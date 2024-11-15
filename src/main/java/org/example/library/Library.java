package org.example.library;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



public class Library {
    private List<Book> books;

    public Library() {
        books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(Book::isAvailable).collect(Collectors.toList());
    }

    public List<Book> searchBooks(String query) {
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(query.toLowerCase()) ||
                        book.getAuthor().toLowerCase().contains(query.toLowerCase()) ||
                        book.getCategory().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }

    public void borrowBook(User user, String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title) && book.isAvailable()) {
                user.borrowBook(book);
                return;
            }
        }
        throw new IllegalArgumentException("Book not available or does not exist.");
    }

    public void returnBook(User user, String title) {
        for (Book book : user.getBorrowedBooks()) {
            if (book.getTitle().equals(title)) {
                user.returnBook(book);
                return;
            }
        }
        throw new IllegalArgumentException("Book not found or not borrowed by this user.");
    }
}

