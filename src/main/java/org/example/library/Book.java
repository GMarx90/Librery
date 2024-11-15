package org.example.library;

import java.time.LocalDate;

public class Book {
    private String title;
    private String author;
    private boolean isAvailable;
    private LocalDate borrowDate;
    private String category;

    public Book(String title, String author, String category) {
        this.title = title;
        this.author = author;
        this.category = category;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void borrow() {
        if (!isAvailable) {
            throw new IllegalStateException("Book is already borrowed.");
        }
        isAvailable = false;
        borrowDate = LocalDate.now();
    }

    public void returnBook() {
        isAvailable = true;
        borrowDate = null;
    }

    public int calculateLateFee() {
        if (borrowDate == null || isAvailable) {
            return 0;
        }
        long daysOverdue = LocalDate.now().toEpochDay() - borrowDate.toEpochDay();
        return daysOverdue > 14 ? (int) (daysOverdue - 14) * 1 : 0; // $1 za każdy dzień po 14 dniach
    }
}
