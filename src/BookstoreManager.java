import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BookstoreManager {

    private static volatile BookstoreManager instance;
    private List<Book> inventory;
    private List<Observer> observers;
    private List<Purchase> transactionHistory;

    private BookstoreManager() {
        this.inventory = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.transactionHistory = new ArrayList<>();
        initializeInventory();
    }

    public static BookstoreManager getInstance() {
        if (instance == null) {
            synchronized (BookstoreManager.class) {
                if (instance == null) {
                    instance = new BookstoreManager();
                }
            }
        }
        return instance;
    }
    
    public void registerObserver(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String eventType, String message) {
        for (Observer observer : observers) {
            observer.update(eventType, message);
        }
    }

    private void initializeInventory() {
        // Physical Printed Books (Weight in Kg)
        addBookSilently(BookFactory.createBook("PRINTED", "Sapiens: A Brief History of Humankind", "Yuval Noah Harari", 28.50, 1.20));
        addBookSilently(BookFactory.createBook("PRINTED", "Steve Jobs", "Walter Isaacson", 32.00, 1.45));
        addBookSilently(BookFactory.createBook("PRINTED", "Dune", "Frank Herbert", 22.00, 0.95));
        addBookSilently(BookFactory.createBook("PRINTED", "To Kill a Mockingbird", "Harper Lee", 18.99, 0.60));
        addBookSilently(BookFactory.createBook("PRINTED", "Thinking, Fast and Slow", "Daniel Kahneman", 24.99, 0.85));
        addBookSilently(BookFactory.createBook("PRINTED", "Clean Code", "Robert C. Martin", 45.99, 0.88));
        addBookSilently(BookFactory.createBook("PRINTED", "Educated", "Tara Westover", 19.99, 0.70));
        addBookSilently(BookFactory.createBook("PRINTED", "Design Patterns", "Erich Gamma", 54.50, 1.10));

        // Digital E-Books (File Size in MB)
        addBookSilently(BookFactory.createBook("EBOOK", "Atomic Habits", "James Clear", 27.00, 4.2));
        addBookSilently(BookFactory.createBook("EBOOK", "1984", "George Orwell", 15.50, 2.8));
        addBookSilently(BookFactory.createBook("EBOOK", "The Psychology of Money", "Morgan Housel", 20.50, 3.5));
        addBookSilently(BookFactory.createBook("EBOOK", "Shoe Dog", "Phil Knight", 21.40, 5.1));
        addBookSilently(BookFactory.createBook("EBOOK", "The Great Gatsby", "F. Scott Fitzgerald", 12.75, 1.9));
        addBookSilently(BookFactory.createBook("EBOOK", "Astrophysics for People in a Hurry", "Neil deGrasse Tyson", 16.80, 2.4));
        addBookSilently(BookFactory.createBook("EBOOK", "Head First Java", "Kathy Sierra", 42.80, 14.6));
    }

    private void addBookSilently(Book book) {
        if (book != null) {
            inventory.add(book);
        }
    }

    public void addBook(Book book) {
        if (book != null) {
            inventory.add(book);
            notifyObservers("BOOK_ADDED", "New title added to catalog: \"" + book.getTitle() + "\"");
        }
    }

    public Book getBookByIndex(int index) {
        if (index >= 0 && index < inventory.size()) {
            return inventory.get(index);
        }
        return null;
    }

    public int getInventorySize() {
        return inventory.size();
    }

    public void recordPurchase(Purchase purchase) {
        if (purchase != null) {
            transactionHistory.add(purchase);
        }
    }


    public double calculateTotalInventoryValue() {
        return inventory.stream()
                .filter(Objects::nonNull)
                .mapToDouble(Book::getPrice)
                .sum();
    }

    public double calculateTotalRevenueFromHistory() {
        return transactionHistory.stream()
                .filter(Objects::nonNull)
                .flatMap(purchase -> purchase.getPurchasedBooks().stream())
                .filter(Objects::nonNull)
                .mapToDouble(Book::getPrice)
                .sum();
    }

    public List<Book> filterBooksByMaxPrice(double maxPrice) {
        return inventory.stream()
                .filter(Objects::nonNull)
                .filter(book -> book.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public void displayInventory() {
        System.out.println("\n----------------------------------------------------------------------------------------------------------------");
        System.out.println("                                            AVAILABLE BOOK COLLECTION                                            ");
        System.out.println("----------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-5s | %-37s | %-20s | %-9s | %-22s\n", "ID", "Book Title", "Author", "Price ($)", "Format Details");
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < inventory.size(); i++) {
            inventory.get(i).displayBook(i + 1);
        }
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }
}