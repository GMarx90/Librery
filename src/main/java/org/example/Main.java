package org.example;

import org.example.library.Book;
import org.example.library.Library;
import org.example.library.User;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Dodajemy przykładowe książki
        library.addBook(new Book("The Great Gatsby", "F. Scott Fitzgerald", "Novel"));
        library.addBook(new Book("1984", "George Orwell", "Dystopian"));
        library.addBook(new Book("To Kill a Mockingbird", "Harper Lee", "Classic"));

        System.out.println("Welcome to the Library System!");
    }
}
