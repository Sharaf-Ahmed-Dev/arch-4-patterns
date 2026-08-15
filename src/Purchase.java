import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private int purchaseId;
    private Customer customer;
    private List<Book> purchasedBooks;

    public Purchase(int purchaseId, Customer customer) {
        this.purchaseId = purchaseId;
        this.customer = customer;
        this.purchasedBooks = new ArrayList<>();
    }

    public void addBook(Book book) {
        if (book != null) {
            purchasedBooks.add(book);
            System.out.println("--> SUCCESS: Added \"" + book.getTitle() + "\" ($" + String.format("%.2f", book.getPrice()) + ") to cart.");
        }
    }

    public Invoice checkout(Calculable calculator, DiscountStrategy discountStrategy) {
        double finalTotal = calculator.calculateTotal(purchasedBooks, discountStrategy);
        Invoice invoice = new Invoice(purchaseId, customer, purchasedBooks, finalTotal, discountStrategy);
        
        BookstoreManager.getInstance().notifyObservers(
            "PURCHASE_COMPLETE", 
            "Transaction #BK-TXN-" + purchaseId + " verified for " + customer.getName() + " | Final Total: $" + String.format("%.2f", finalTotal)
        );

        return invoice;
    }

    public List<Book> getPurchasedBooks() { return purchasedBooks; }
    public int getPurchaseId() { return purchaseId; }
}