public interface DiscountStrategy {
    double applyDiscount(double rawTotal);
    String getStrategyName();
}