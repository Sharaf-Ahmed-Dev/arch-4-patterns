import java.util.List;

public class Invoice {
    private int invoiceId;
    private Customer customer;
    private List<Book> books;
    private double totalAmount;
    private DiscountStrategy discountStrategy;

    public Invoice(int invoiceId, Customer customer, List<Book> books, double totalAmount, DiscountStrategy discountStrategy) {
        this.invoiceId = invoiceId;
        this.customer = customer;
        this.books = books;
        this.totalAmount = totalAmount;
        this.discountStrategy = discountStrategy;
    }

    public int getInvoiceId() { return invoiceId; }
    public Customer getCustomer() { return customer; }
    public List<Book> getBooks() { return books; }
    public double getTotalAmount() { return totalAmount; }
    public DiscountStrategy getDiscountStrategy() { return discountStrategy; }
}