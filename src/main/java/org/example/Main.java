package org.example;

import org.example.library.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Load books from JSON file
        try {
            library.getBooks().addAll(DataPersistance.loadLibrary());
        } catch (IOException e) {
            System.out.println("Failed to load library data: " + e.getMessage());
        }

        System.out.println("Welcome to the Library System!");

        while (running) {
            System.out.println("\nSelect an option:");
            System.out.println("1. View available books");
            System.out.println("2. Search books");
            System.out.println("3. Borrow a book");
            System.out.println("4. Return a book");
            System.out.println("5. Add a new book");
            System.out.println("6. Exit");

            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.println("\nAvailable books:");
                    library.getAvailableBooks().forEach(book -> System.out.println("- " + book.getTitle() + " by " + book.getAuthor()));
                    break;
                case 2:
                    System.out.print("\nEnter search query: ");
                    String query = scanner.nextLine();
                    library.searchBooks(query).forEach(book -> System.out.println("- " + book.getTitle() + " by " + book.getAuthor()));
                    break;
                case 3:
                    System.out.print("\nEnter the title of the book to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    try {
                        library.borrowBook(new User("John Doe"), borrowTitle);
                        System.out.println("You borrowed \"" + borrowTitle + "\".");
                    } catch (IllegalArgumentException | IllegalStateException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.print("\nEnter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    try {
                        library.returnBook(new User("John Doe"), returnTitle);
                        System.out.println("You returned \"" + returnTitle + "\".");
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    System.out.print("\nEnter the title of the new book: ");
                    String newTitle = scanner.nextLine();
                    System.out.print("Enter the author of the new book: ");
                    String newAuthor = scanner.nextLine();
                    System.out.print("Enter the category of the new book: ");
                    String category = scanner.nextLine();
                    library.addBook(new Book(newTitle, newAuthor, category));
                    System.out.println("Added \"" + newTitle + "\" by " + newAuthor + " to the library.");
                    break;
                case 6:
                    running = false;
                    // Save books to JSON file
                    try {
                        DataPersistance.saveLibrary(library.getBooks());
                    } catch (IOException e) {
                        System.out.println("Failed to save library data: " + e.getMessage());
                    }
                    System.out.println("Exiting the Library System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
