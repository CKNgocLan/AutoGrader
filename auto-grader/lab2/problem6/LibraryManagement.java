
import java.util.*;

public class LibraryManagement {
    private List<User> users;
    private List<Book> books;
    private List<BorrowingRecord> borrowingRecords;

    public LibraryManagement() {
        users = new ArrayList<>();
        books = new ArrayList<>();
        borrowingRecords = new ArrayList<>();
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void addBorrowingRecord(BorrowingRecord borrowingRecord) {
        this.borrowingRecords.add(borrowingRecord);
    }

    public List<Book> getBorrowingBooks(User user) {
        return this.borrowingRecords.stream()
            .filter(record -> record.getUser().equals(user))
            .map(record -> record.getBook())
            .toList();
    }

    private boolean isValidUser(User user) {
        return this.users.contains(user);
    }

    private boolean isUserEligibleToBorrow(User user) {
        return isValidUser(user)
            && borrowingRecords.stream()
                .filter(record -> record.getUser().equals(user) && record.isOverdue())
                .toList().size() <= 0;
    }

    public void showAllUsers() {
        this.users.stream().forEach(System.out::println);
    }

    public void showAllBooks() {
        this.books.stream().forEach(System.out::println);
    }

    public void showAllBorrowingRecords() {
        this.borrowingRecords.stream().forEach(System.out::println);
    }

    // public void removeUser(User user) {
    //     users.removeIf(u -> u.equals(user));

    // }

    // public void removeBook(Book book) {
    //     books.removeIf(b -> b.equals(book));

    // }

    // public boolean checkAvailable(User targetUser, Book targetBook) {
    //     boolean isUserValid = false;
    //     for (User existingUser : users) {
    //         if (existingUser.equals(targetUser)) {
    //             isUserValid = true;
    //             break;
    //         }
    //     }
    //     if (!isUserValid)
    //         return false;
    //     boolean isBookValid = false;
    //     for (Book existingBook : books) {
    //         if (existingBook.equals(targetBook)) {
    //             isBookValid = true;
    //             break;
    //         }
    //     }

    //     if (!isBookValid) {
    //         return false;
    //     }
    //     for (BorrowingRecord record : borrowingRecords) {
    //         if (record.getBook().equals(targetBook))
    //             return false;
    //     }
    //     return true;
    // }
}
