public class NoDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double rawTotal) { return 0.0; }
    @Override
    public String getStrategyName() { return "Regular Purchase - No Discount"; }
}