public class SeasonalDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount * 0.85; // 15% OFF
    }

    @Override
    public String getStrategyName() {
        return "Seasonal Festival Special (15% OFF)";
    }
}