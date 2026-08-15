import java.util.List;

public class BillCalculator implements Calculable {
    @Override
    public double calculateTotal(List<Book> books, DiscountStrategy discountStrategy) {
        double subtotal = 0.0;
        for (Book book : books) {
            subtotal += book.getPrice();
        }
        double discountAmount = discountStrategy.applyDiscount(subtotal);
        return subtotal - discountAmount;
    }
}