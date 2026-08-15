import java.util.List;

public interface Calculable {
    double calculateTotal(List<Book> books, DiscountStrategy discountStrategy);
}