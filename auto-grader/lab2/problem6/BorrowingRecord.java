
import java.time.LocalDate;

public class BorrowingRecord {
    private User user;
    private Book book;
    private LocalDate borrowingDate;
    private LocalDate dueDate;

    public BorrowingRecord(User borrowingUser, Book borrowedBook, LocalDate dueDate) {
        this.book = borrowedBook;
        this.user = borrowingUser;
        this.borrowingDate = LocalDate.now();
        this.dueDate = dueDate;
    }

    public boolean isOverdue() {
        return this.dueDate.isBefore(LocalDate.now());
    }

    public boolean equals(BorrowingRecord borrowingRecord) {
        return this.user.equals(borrowingRecord.getUser())
            && this.book.equals(borrowingRecord.getBook());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LocalDate getBorrowingDate() {
        return borrowingDate;
    }

    public void setBorrowingDate(LocalDate borrowingDate) {
        this.borrowingDate = borrowingDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "User: %s \n Book: %s \n Borrowing Date: % \n Due Date: %s".formatted(user, book, borrowingDate, dueDate);
    }
}