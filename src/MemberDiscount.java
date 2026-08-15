public class MemberDiscount implements DiscountStrategy {

    @Override
    public double applyDiscount(double totalAmount) {
        return totalAmount * 0.90; 
    }

    @Override
    public String getStrategyName() {
        return "Premium Member Discount (10% OFF)";
    }
}